package ru.fssprus.r82.swing.dialogs.questionList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.table.TablePanelController;
import ru.fssprus.r82.swing.table.UpdatableController;
import ru.fssprus.r82.swing.utils.MessageBox;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionListController extends CommonController<QuestionListDialog> implements UpdatableController {
	private static final String FROM_TEXT = " из ";
	private static final int ENTRIES_FOR_PAGE = AppConstants.TABLE_ROWS_LIMIT;
	private int currentPage;
	private int totalPages;

	private List<Question> questionsOnScreenList;

	private Question currentQuestion = null;
	private boolean questionEditing = false;

	private int totalQuestions;

	public QuestionListController(QuestionListDialog dialog) {
		super(dialog);

		TablePanelController tablePanelController = new TablePanelController(dialog.getTabPanel());
		tablePanelController.setSubscriber(this);

		blockQuestionEditPanel(true);

		updateDialog();
	}

	@Override
	protected void setListeners() {
		dialog.getBtnFilter().addActionListener(listener -> updateDialog());
		dialog.getBtnDiscardQuestionEditChanges().addActionListener(listener -> doDiscardChangesAction());
		dialog.getBtnEditQuestion().addActionListener(listener -> doEditAction());
		dialog.getBtnClearFilters().addActionListener(listener -> doClearFiltersAction());
		dialog.getBtnSaveQuestion().addActionListener(listener -> doSaveQuestionAction());
	}

	private void doEditAction() {
		if (dialog.getTable().getLastSelectedIndex() == AppConstants.NO_ROW_SELECTED)
			return;
		if (questionEditing) {
			blockQuestionEditPanel(false);
		} else {
			blockQuestionEditPanel(true);
		}
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

		updateDialog();
		blockQuestionEditPanel(true);
	}

	private void doClearFiltersAction() {
		clearQuestionEditPanelContents();
		dialog.getTable().unselectAll();

		dialog.getTfId().setText(null);
		dialog.getTfQuestionName().setText(null);
		dialog.getTfSpecs().setText(null);
		dialog.getTfLevels().setText(null);

		updateDialog();
	}

	private void doDiscardChangesAction() {
		clearQuestionEditPanelContents();
		showQuestion(currentQuestion);
	}

	private int getLimitStart() {
		if (totalQuestions <= ENTRIES_FOR_PAGE)
			return AppConstants.NO_SQL_LIMIT_START_SPECIFIED;

		return currentPage * ENTRIES_FOR_PAGE;
	}

	private int getLimitMax() {
		if (totalQuestions <= ENTRIES_FOR_PAGE)
			return AppConstants.NO_SQL_LIMIT_MAX_SPECIFIED;

		return ENTRIES_FOR_PAGE;
	}
	
	private void updateDialog() {
		clearQuestionEditPanelContents();
		updatePageInfo();
		updateTable();
	}

	private void updateTable() {
		dialog.getTable().unselectAll();

		dialog.getTable().getTabModel().clearTable();

		fillQuestionOnScreenList();
		convertUserInputAndAddToTable();
	}
	
	private void updatePageInfo() {
		dialog.getTabPanel().getTfPage().setText(String.valueOf(currentPage + 1));
		dialog.getTabPanel().getLblPagesTotal().setText(FROM_TEXT + countTotalPages());
	}

	private void fillQuestionOnScreenList() {
		QuestionService questionService = new QuestionService();

		questionsOnScreenList = questionService.getByNameSpecListAndId(getLimitStart(), getLimitMax(),
				getQuestionTitleFromFilterUI(), getSetsFromFilterUI(), getIdFromFilterUI());
	}

	private String getQuestionTitleFromFilterUI() {
		return dialog.getTfQuestionName().getText();
	}

	private Set<QuestionSet> getSetsFromFilterUI() {
		String setsText = dialog.getTfSpecs().getText();
		return parseSets(setsText);
	}

	private Long getIdFromFilterUI() {
		String idText = dialog.getTfId().getText();
		Long id = 0l;

		if (!idText.isEmpty())
			id = Long.parseLong(idText);

		return id;
	}

	private int countTotalPages() {
		QuestionService questionService = new QuestionService();

		totalQuestions = questionService.countByNameSpecListAndId(getQuestionTitleFromFilterUI(), getSetsFromFilterUI(),
				getIdFromFilterUI());

		return this.totalPages = totalQuestions / ENTRIES_FOR_PAGE + 1;
	}

	private void blockQuestionEditPanel(boolean block) {
		questionEditing = block;
		dialog.getTaQuestion().setEditable(!block);

		dialog.getAccbSpecNames().setEditable(!block);

		for (int i = 0; i < AppConstants.MAX_ANSWERS_AMOUNT; i++) {
			dialog.getTfAnsList().get(i).setEditable(!block);
			dialog.getCbAnsList().get(i).setEnabled(!block);
		}
		dialog.getBtnSaveQuestion().setEnabled(!block);
		dialog.getBtnDiscardQuestionEditChanges().setEnabled(!block);
	}
	
	private void setAnswersToUI(Question question) {
		List<Answer> answers = new ArrayList<Answer>(question.getAnswers());
		answers.forEach((n)->
		fillUIAnswers(answers.indexOf(n), n.getTitle(), n.getIsCorrect()));
	}
	
	private void showQuestion(Question currentQuestion) {
		setAnswersToUI(currentQuestion);
		
		dialog.getTaQuestion().setText(currentQuestion.getTitle());
		
		dialog.getAccbSpecNames().setSelectedItem(currentQuestion.getQuestionSet().getName());
	}
	
	private void fillUIAnswers(int index, String title, boolean isSelected) {
		dialog.getTfAnsList().get(index).setText(title);
		dialog.getTfAnsList().get(index).setCaretPosition(0);
		dialog.getCbAnsList().get(index).setSelected(isSelected);
	}

	private void clearQuestionEditPanelContents() {
		dialog.getTaQuestion().setText(null);
		for (int i = 0; i < AppConstants.MAX_ANSWERS_AMOUNT; i++) {
			dialog.getTfAnsList().get(i).setText(null);
			dialog.getCbAnsList().get(i).setSelected(false);
		}

		dialog.getAccbSpecNames().setSelectedIndex(0);
	}
	
	
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

	private Question configureQuestionFromQuestionEditUI() {
		Question question = new Question();

		if (currentQuestion.getId() != null)
			question.setId(currentQuestion.getId());

		question.setTitle(qetQuestionTitleFromQuestionEditUI());

		question.setAnswers(new HashSet<Answer>(getAnswersFromQuestionEditUI(question)));

		question.setQuestionSet(getQuestionSetFromQuestionEditUI());

		return question;
	}

	private QuestionSet getQuestionSetFromQuestionEditUI() {
		QuestionSetService setService = new QuestionSetService();

		List<QuestionSet> sets = setService.getByName(dialog.getAccbSpecNames().getSelectedItem().toString());

		QuestionSet setToAdd = null;
		if (sets.size() == 0) {
			setToAdd = new QuestionSet();
			setToAdd.setName(String.valueOf(dialog.getAccbSpecNames().getSelectedItem()));
		} else {
			setToAdd = sets.get(0);
		}
		return setToAdd;
	}

	//TODO
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
		if (dialog.getAccbSpecNames().getSelectedItem().toString().isEmpty())
			return false;

		return true;

	}

	private Set<QuestionSet> parseSets(String setsText) {
		if (!setsText.isEmpty()) {
			QuestionSetService setService = new QuestionSetService();
			String[] setsTextList = setsText.trim().replaceAll(" ", "").split(",");

			Set<QuestionSet> setsList = new HashSet<QuestionSet>();
			for (int i = 0; i < setsTextList.length; i++)
				setsList.addAll(setService.getByName(setsTextList[i]));

			return setsList;
		}
		return null;

	}

	private void convertUserInputAndAddToTable() {
		for (int i = 0; i < questionsOnScreenList.size(); i++) {
			Question question = questionsOnScreenList.get(i);

			Long id = question.getId();
			String title = question.getTitle();
			QuestionSet questionSet = question.getQuestionSet();

			String setString = questionSet.getName();

			Object[] row = { id, title, setString };

			dialog.getTableModel().setRow(row, i);

			dialog.getTableModel().update();
		}
	}

	private void addBlankQuestion() {
		Question q = new Question();
		q.setAnswers(new HashSet<Answer>(AppConstants.MAX_ANSWERS_AMOUNT));
		q.setQuestionSet(new QuestionSet());

		questionsOnScreenList.add(q);

		dialog.getBtnEditQuestion().doClick();
	}

	@Override
	public void edit(int index) {
		if (index >= questionsOnScreenList.size())
			addBlankQuestion();
		clearQuestionEditPanelContents();
		currentQuestion = questionsOnScreenList.get(index);
		doDiscardChangesAction();

	}

	@Override
	public void nextPage() {
		if (currentPage + 1 < totalPages)
			currentPage++;
		updateDialog();
		blockQuestionEditPanel(true);
	}

	@Override
	public void previousPage() {
		if (currentPage > 0)
			currentPage--;
		updateDialog();
		blockQuestionEditPanel(true);
	}

	@Override
	public void delete(int index) {
		if (MessageBox.showConfirmQuestionDelete(dialog)) {
			QuestionService service = new QuestionService();
			service.delete(currentQuestion);
		}
		updateDialog();

	}

	@Override
	public void goToPage(int page) {
		if (page <= totalPages && page > 0)
			currentPage = page - 1;

		updateDialog();
	}

}
