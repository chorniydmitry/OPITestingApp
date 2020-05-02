package ru.fssprus.r82.swing.dialogs.questionEdit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.dialogs.DialogBuilder;
import ru.fssprus.r82.swing.utils.MessageBox;
import ru.fssprus.r82.utils.AppConstants;

public class QuestionEditController extends CommonController<QuestionEditDialog> {

	private Question questionToEdit;

	public QuestionEditController(QuestionEditDialog dialog, Question questionToEdit) {
		super(dialog);

		this.questionToEdit = questionToEdit;

		fillCbsWithAvailibleSets();

		loadQuestion();
	}

	@Override
	protected void setListeners() {
		dialog.getBtnCancel().addActionListener(l -> btnCancelAction());
		dialog.getBtnSaveQuestion().addActionListener(l -> btnSaveAction());
		dialog.getBtnAddImage().addActionListener(l -> btnAddImageAction());
	}

	private void btnCancelAction() {
		dialog.dispose();
		DialogBuilder.showQuestionListDialog();
	}

	private void btnSaveAction() {
		questionToEdit.setAnswers(new HashSet<>(getAnswersFromQuestionEditUI()));
		
		System.out.println(getAnswersFromQuestionEditUI().size());
		System.out.println(questionToEdit.getAnswers().size());
		
		
		questionToEdit.setTitle(qetQuestionTitleFromQuestionEditUI());
		questionToEdit.setQuestionSet(getQuestionSetFromQuestionEditUI());

		QuestionService qService = new QuestionService();
		if (checkQuestionToSave()) {

			if (questionToEdit.getId() != null)
				qService.update(questionToEdit);
			else
				qService.save(questionToEdit);

			dialog.dispose();

			DialogBuilder.showQuestionListDialog();

		}
	}

	// TODO:
	private void btnAddImageAction(){
		//MessageBox.showCommonErrorMessage(dialog, "Функционал пока не доступен!");
		int caretPos = dialog.getTaQuestion().getCaretPosition();
		String currentText = dialog.getTaQuestion().getText();

		File fileSelected = selectImageFile();
		File newFile = copySelectedFile(fileSelected);
		
		String fileTag = "<img src=\""+newFile.getAbsolutePath()+"\">";
		
		StringBuffer buffer = new StringBuffer(currentText);
		buffer.insert(caretPos, fileTag);
		
		dialog.getTaQuestion().setText(buffer.toString());
		
	   
	}
	 
	private File selectImageFile() {
		JFileChooser fChooser = new JFileChooser();
		fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fChooser.setMultiSelectionEnabled(false);
		fChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG, PNG and GIF images", "jpg", "jpeg", "png", "gif");
		fChooser.addChoosableFileFilter(filter);
		int selection = fChooser.showOpenDialog(dialog);
		if (selection == JFileChooser.APPROVE_OPTION) {
			return fChooser.getSelectedFile();
		} else return null;
	}
	
	private File copySelectedFile(File selectedFile) {
		try {
			File toSaveFile = new File("images/quest_illustration_" + System.currentTimeMillis() + "." + selectedFile.getName().split("\\.")[1]);
			Files.copy(selectedFile.toPath(), toSaveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			return toSaveFile;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void fillCbsWithAvailibleSets() {
		QuestionSetService setService = new QuestionSetService();
		List<QuestionSet> questionSets = setService.getAll();

		dialog.getCbAvailibleSetNames().addItem(null);
		for (QuestionSet questionSet : questionSets) {
			dialog.getCbAvailibleSetNames().addItem(new String(questionSet.getName()));
		}
	}

	private void loadQuestion() {
		if (questionToEdit == null)
			configuteBlankQuestion();
		
		System.out.println(questionToEdit);
		dialog.getTaQuestion().setText(questionToEdit.getTitle());
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

	private boolean checkQuestionToSave() {
		String errorMessage = "";

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Question>> violations = validator.validate(questionToEdit);

		Set<ConstraintViolation<Answer>> answersViolations = getAnswersViolations();

		if (violations.size() == 0 && answersViolations.size() == 0) {
			return true;
		}

		for (ConstraintViolation<Question> violation : violations)
			errorMessage += violation.getMessage() + "\n";

		errorMessage += "\n";

		for (ConstraintViolation<Answer> violation : answersViolations)
			errorMessage += violation.getMessage() + "\n";

		errorMessage += "\n";

		if (!checkIfCorrectAnswerIsSet())
			errorMessage += AppConstants.VALID_QUEST_NO_CORRECT_ANSWER + "\n";

		MessageBox.showValidationFaildMessage(dialog, errorMessage);

		return false;
	}

	private boolean checkIfCorrectAnswerIsSet() {
		for (JCheckBox cb : dialog.getCbAnsList())
			if (cb.isSelected())
				return true;
		return false;
	}

	private Set<ConstraintViolation<Answer>> getAnswersViolations() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Answer>> allViolations = new HashSet<>();

		for (Answer ans : questionToEdit.getAnswers())
			allViolations.addAll(validator.validate(ans));

		return allViolations;
	}

}