package ru.fssprus.r82.ui.dialogs.questionEdit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.ui.dialogs.CommonController;
import ru.fssprus.r82.ui.dialogs.DialogBuilder;
import ru.fssprus.r82.ui.utils.ComboboxToolTipRenderer;
import ru.fssprus.r82.ui.utils.JImageFileChooser;
import ru.fssprus.r82.ui.utils.MessageBox;
import ru.fssprus.r82.utils.AppConstants;

public class QuestionEditController extends CommonController<QuestionEditDialog> {

	private Question questionToEdit;
	private File fileToSave;
	
	private Map<String, String> filters;

	public QuestionEditController(QuestionEditDialog dialog, Question questionToEdit, Map<String, String> filters) {
		super(dialog);

		this.questionToEdit = questionToEdit;

		fillCbsWithAvailibleSets();
		
		this.filters = filters;
		
		loadQuestion();
	}

	@Override
	protected void setListeners() {
		addOnDisposeListener();
		dialog.getBtnCancel().addActionListener(l -> doCancelAction());
		dialog.getBtnSaveQuestion().addActionListener(l -> doSaveAction());
		dialog.getBtnAddImage().addActionListener(l -> doAddImageAction());
	}
	
	private void addOnDisposeListener() {
		dialog.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	DialogBuilder.showQuestionListDialog(filters);
		    }
		});
	}

	private void doSaveAction() {
		questionToEdit.setAnswers(new HashSet<>(getAnswersFromQuestionEditUI()));

		questionToEdit.setTitle(qetQuestionTitleFromQuestionEditUI());
		questionToEdit.setQuestionSet(getQuestionSetFromQuestionEditUI());

		checkAndSaveFile();

		QuestionService qService = new QuestionService();
		if (checkQuestionToSave()) {
			if (questionToEdit.getId() != null)
				qService.update(questionToEdit);
			else
				qService.save(questionToEdit);

			dialog.dispose();

		}
	}
	
	private void doCancelAction() {
		dialog.dispose();
	}

	// TODO:
	private void doAddImageAction() {
		JImageFileChooser jIFC = new JImageFileChooser();
		fileToSave = jIFC.selectImageFile(dialog.getParent());
	
		String fileTag = fileToSave.getPath();
	
		dialog.getTfImageLink().setText(fileTag);
	}
	
	//TODO вынести
	private void fillCbsWithAvailibleSets() {
		QuestionSetService setService = new QuestionSetService();
		
		dialog.getCbAvailibleSetNames().addItem(null);

		ArrayList<String> keywords = new ArrayList<String>();
		setService.getAll().forEach((n) -> keywords.add(n.getName()));
		
		ComboboxToolTipRenderer renderer = new ComboboxToolTipRenderer();
		dialog.getCbAvailibleSetNames().setRenderer(renderer);

		List<String> tooltips = new ArrayList<>();
		tooltips.add(null);
		
		keywords.forEach((n) -> {
			dialog.getCbAvailibleSetNames().addItem(n);
			tooltips.add(n);
		});
		
		renderer.setTooltips(tooltips);
	}

	private void loadQuestion() {
		if (questionToEdit == null)
			configuteBlankQuestion();

		dialog.getTaQuestion().setText(questionToEdit.getTitle());
		dialog.getTfImageLink().setText(questionToEdit.getImageLink());
		setAnswersToUI(questionToEdit);
		setQuestionSetToUI();
	}

	private void configuteBlankQuestion() {
		questionToEdit = new Question();
		questionToEdit.setAnswers(new HashSet<>());
		questionToEdit.setQuestionSet(new QuestionSet());
	}

	private void setQuestionSetToUI() {
		String questionSetName = questionToEdit.getQuestionSet().getName();
		dialog.getCbAvailibleSetNames().setSelectedItem(questionSetName);
	}

	private void setAnswersToUI(Question question) {
		List<Answer> answers = new ArrayList<Answer>(question.getAnswers());
		answers.forEach((n) -> fillUIAnswers(answers.indexOf(n), n.getTitle(), n.getIsCorrect()));
	}

	private void fillUIAnswers(int index, String title, boolean isSelected) {
		dialog.getTaAnsList().get(index).setText(title);
		dialog.getTaAnsList().get(index).setCaretPosition(0);
		dialog.getCbAnsList().get(index).setSelected(isSelected);
	}

	private List<Answer> getAnswersFromQuestionEditUI() {
		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < dialog.getTaAnsList().size(); i++) {
			String ansText = dialog.getTaAnsList().get(i).getText();
			if (!ansText.isEmpty()) {
				Answer answer = new Answer();
				answer.setQuestion(questionToEdit);
				answer.setTitle(ansText);
				answer.setIsCorrect(dialog.getCbAnsList().get(i).isSelected());

				answers.add(answer);
			}
		}
		return answers;
	}

	private String qetQuestionTitleFromQuestionEditUI() {
		return dialog.getTaQuestion().getText();
	}

	private QuestionSet getQuestionSetFromQuestionEditUI() {
		QuestionSetService setService = new QuestionSetService();

		List<QuestionSet> sets = setService
				.getByName(String.valueOf(dialog.getCbAvailibleSetNames().getSelectedItem()));

		return sets.size() == 0 ? null : sets.get(0);
	}

	private void checkAndSaveFile() {
		if (fileToSave == null)
			return;
		
		String oldImageLink = questionToEdit.getImageLink();
	
		if (oldImageLink != null && !oldImageLink.isEmpty())
			deleteUnusedFile();
	
		File toSaveFile = new File(
				"images/quest_illustration_" + System.currentTimeMillis() + "." + fileToSave.getName().split("\\.")[1]);
		try {
			Files.copy(fileToSave.toPath(), toSaveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			MessageBox.showErrorMessage(dialog.getParent(), AppConstants.CANNOT_SAVEFILE_ERROR);
			e.printStackTrace();
			return;
		}
		
		questionToEdit.setImageLink(toSaveFile.toString());
	
	}

	private boolean checkQuestionToSave() {
		String errorMessage = "";

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Question>> questionViolations = validator.validate(questionToEdit);

		Set<ConstraintViolation<Answer>> answersViolations = getAnswersViolations();
		
		//TODO: move to custom validator
		if (!checkIfCorrectAnswerIsSet()) 
			errorMessage += AppConstants.VALID_QUEST_NO_CORRECT_ANSWER + AppConstants.EOL;
		
		if(checkIfAnswersHaveDublicates())
			errorMessage += AppConstants.VALID_QUEST_ANSWERS_HAS_DUBLICATES + AppConstants.EOL;
		
		if (questionViolations.size() == 0 && answersViolations.size() == 0 && errorMessage.isEmpty()) {
			return true;
		}

		for (ConstraintViolation<Question> violation : questionViolations)
			errorMessage += violation.getMessage() + AppConstants.EOL;

		errorMessage += AppConstants.EOL;

		for (ConstraintViolation<Answer> violation : answersViolations)
			errorMessage += violation.getMessage() + AppConstants.EOL;

		errorMessage += AppConstants.EOL;



		MessageBox.showValidationFaildMessage(dialog, errorMessage);

		return false;
	}

	//TODO: move to custom validator
	private boolean checkIfCorrectAnswerIsSet() {
		for (JCheckBox cb : dialog.getCbAnsList())
			if (cb.isSelected())
				return true;
		return false;
	}
	
	//TODO: move to custom validator
	private boolean checkIfAnswersHaveDublicates() {
		ArrayList<String> answersTextsList = new ArrayList<>();
		
		for(Answer ans: questionToEdit.getAnswers()) {
			if(answersTextsList.indexOf(ans.getTitle()) == -1)
				answersTextsList.add(ans.getTitle());
			else
				return true;
		}
		
		
		return false;
	}

	private void deleteUnusedFile() {
		File f = new File(questionToEdit.getImageLink());
		f.delete();
	}

	private Set<ConstraintViolation<Answer>> getAnswersViolations() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Answer>> allViolations = new HashSet<>();

		
		for (Answer ans : questionToEdit.getAnswers()) {
			allViolations.addAll(validator.validate(ans));
		}

		return allViolations;
	}
	
	

}
