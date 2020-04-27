package ru.fssprus.r82.swing.dialogs.questionEdit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComboBox;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.utils.MessageBox;
import ru.fssprus.r82.utils.AppConstants;

public class QuestionEditController extends CommonController<QuestionEditDialog> {
	
	private Question questionToEdit;
	
	public QuestionEditController(QuestionEditDialog dialog, Question questionToEdit) {
		super(dialog);
		
		this.questionToEdit = questionToEdit;
//		
		loadQuestion();
//		
		initTfSpecNames();
	}

	@Override
	protected void setListeners() {
		// TODO Auto-generated method stub
		
	}
	
	private void initTfSpecNames() {
		QuestionSetService setService = new QuestionSetService();
		
		ArrayList<String> keywords = new ArrayList<String>();
		setService.getAll().forEach((n) -> keywords.add(n.getName()));
		
		for (String string : keywords) {
			dialog.getCbSpecNames().addItem(string);
		}
	}
	
	private void loadQuestion() {
		dialog.getTaQuestion().setText(questionToEdit.getTitle());
		setAnswersToUI(questionToEdit);
	}
	
	private void setAnswersToUI(Question question) {
		List<Answer> answers = new ArrayList<Answer>(question.getAnswers());
		answers.forEach((n)->
		fillUIAnswers(answers.indexOf(n), n.getTitle(), n.getIsCorrect()));
	}
	
	private void fillUIAnswers(int index, String title, boolean isSelected) {
		dialog.getTfAnsList().get(index).setText(title);
		dialog.getTfAnsList().get(index).setCaretPosition(0);
		dialog.getCbAnsList().get(index).setSelected(isSelected);
	}
	
	// OLD UNCHECKED BELOW
	private List<Answer> getAnswersFromQuestionEditUI(Question question) {
		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < dialog.getTfAnsList().size(); i++) {
			String ansText = dialog.getTfAnsList().get(i).getText();
			if (!ansText.isEmpty()) {
				Answer answer = new Answer();
				answer.setQuestion(question);
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
	
	//TODO сделать нормальную валидацию
		private boolean validateQuestionSave() {
			// Валидация текста вопроса
			// Длина текста вопроса слишком маленькая
			if (dialog.getTaQuestion().getText().length() < AppConstants.QUESTION_TEXT_MIN_LENGTH)
				return false;

			// ----------------
			// Валидация ответов
			boolean isAnyAnswerAsCorrectSelected = false;
			int amountOfAnswers = 0;
			for (int i = 0; i < AppConstants.MAX_ANSWERS_AMOUNT; i++) {
				String currAnswer = dialog.getTfAnsList().get(i).getText();

				if (!currAnswer.isEmpty()) {
					amountOfAnswers++;
					// Пустой вопрос помечен как верный
				} else if (dialog.getCbAnsList().get(i).isSelected()) {
					return false;
				}

				if (dialog.getCbAnsList().get(i).isSelected())
					isAnyAnswerAsCorrectSelected = true;
			}
			// Не заполнено минимальное количество ответов
			if (amountOfAnswers < AppConstants.MIN_ANSWERS_AMOUNT)
				return false;

			// Ни один из ответов не помечен как верный
			if (!isAnyAnswerAsCorrectSelected)
				return false;

			// ----------------
			// Валидация спецализации
			// Не заполнена специализация
			if (dialog.getCbSpecNames().getSelectedItem().toString().isEmpty())
				return false;

			return true;

		}
		
		private QuestionSet getQuestionSetFromQuestionEditUI() {
			QuestionSetService setService = new QuestionSetService();

			List<QuestionSet> sets = setService.getByName(dialog.getCbSpecNames().getSelectedItem().toString());

			QuestionSet setToAdd = null;
			if (sets.size() == 0) {
				setToAdd = new QuestionSet();
				setToAdd.setName(String.valueOf(dialog.getCbSpecNames().getSelectedItem()));
			} else {
				setToAdd = sets.get(0);
			}
			return setToAdd;
		}
		
		private Question configureQuestionFromQuestionEditUI() {
			Question question = new Question();

			if (questionToEdit.getId() != null)
				question.setId(questionToEdit.getId());

			question.setTitle(qetQuestionTitleFromQuestionEditUI());

			question.setAnswers(new HashSet<Answer>(getAnswersFromQuestionEditUI(question)));

			question.setQuestionSet(getQuestionSetFromQuestionEditUI());

			return question;
		}
		
		private void doSaveQuestionAction() {
			if (!validateQuestionSave()) {
				MessageBox.showWrongQuestionSpecifiedErrorDialog(dialog);
				return;
			}

			QuestionService service = new QuestionService();
			Question questionToSave = configureQuestionFromQuestionEditUI();

			if (questionToSave.getId() == null)
				service.save(questionToSave);
			else
				service.update(questionToSave.getId(), questionToSave);

		//	blockQuestionEditPanel(true);
		}

	
}
