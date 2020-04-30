package ru.fssprus.r82.swing.dialogs.questionEdit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		questionToEdit.setTitle(qetQuestionTitleFromQuestionEditUI());
		questionToEdit.setQuestionSet(getQuestionSetFromQuestionEditUI());

		QuestionService qService = new QuestionService();
		if (checkQuestionToSave()) {
			System.out.println(questionToEdit.getId());
			
			if(questionToEdit.getId() != null)
				qService.update(questionToEdit.getId(), questionToEdit);
			else qService.save(questionToEdit);
			
			dialog.dispose();
			
			DialogBuilder.showQuestionListDialog();
			
		}
	}

	//TODO:
	private void btnAddImageAction() {
		MessageBox.showCommonErrorMessage(dialog, "Функционал пока не доступен!");
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
		if(questionToEdit == null)
			configuteBlankQuestion();
		dialog.getTaQuestion().setText(questionToEdit.getTitle());
		setAnswersToUI(questionToEdit);
		setQuestionSetToUI();
	}
	
	private void configuteBlankQuestion() {
		questionToEdit = new Question();
		questionToEdit.setAnswers(new HashSet<>());
		questionToEdit.setQuestionSet(new QuestionSet());;
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

		List<QuestionSet> sets = setService.getByName(
				String.valueOf(dialog.getCbAvailibleSetNames().getSelectedItem()));

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

		MessageBox.showValidationFaildMessage(dialog, errorMessage);

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
