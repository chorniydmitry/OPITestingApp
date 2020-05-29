package ru.fssprus.r82.ui.dialogs.questionList;

import java.awt.CardLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.entity.Answer;
import ru.fssprus.r82.entity.Question;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.ui.dialogs.CommonController;
import ru.fssprus.r82.ui.dialogs.DialogBuilder;
import ru.fssprus.r82.ui.table.TablePanelController;
import ru.fssprus.r82.ui.table.UpdatableController;
import ru.fssprus.r82.ui.utils.MessageBox;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionListController extends CommonController<QuestionListDialog> implements UpdatableController {

	public QuestionListController(QuestionListDialog dialog) {
		super(dialog);
		// TODO Auto-generated constructor stub
	}
//	private static final String FROM_TEXT = " из ";
//	private static final int ENTRIES_FOR_PAGE = AppConstants.TABLE_ROWS_LIMIT;
//	private int currentPage;
//	private int totalPages;
//
//	private List<Question> questionsOnScreenList;
//
//	private Question currentQuestion = null;
//
//	private int totalQuestions;
//
//	public QuestionListController(QuestionListDialog dialog) {
//		super(dialog);
//
//		TablePanelController tablePanelController = new TablePanelController(dialog.getPanelQuestionList().getTabPanel());
//		tablePanelController.setSubscriber(this);
//		
//		dialog.getPanelQuestionList().getTable().setColumnMultiline(1, true);
//
//		updateDialog();
//	}
//
//	@Override
//	protected void setListeners() {
//		dialog.getPanelQuestionList().getBtnFilter().addActionListener(listener -> updateDialog());
//		dialog.getPanelQuestionList().getBtnClearFilters().addActionListener(listener -> doClearFiltersAction());
//	}
//
//
//	private void doClearFiltersAction() {
//		dialog.getPanelQuestionList().getTable().unselectAll();
//
//		dialog.getPanelQuestionList().getTfId().setText(null);
//		dialog.getPanelQuestionList().getTfQuestionName().setText(null);
//		dialog.getPanelQuestionList().getTfSpecs().setText(null);
//
//		updateDialog();
//	}
//
//	private int getLimitStart() {
//		if (totalQuestions <= ENTRIES_FOR_PAGE)
//			return AppConstants.NO_SQL_LIMIT_START_SPECIFIED;
//
//		return currentPage * ENTRIES_FOR_PAGE;
//	}
//
//	private int getLimitMax() {
//		if (totalQuestions <= ENTRIES_FOR_PAGE)
//			return AppConstants.NO_SQL_LIMIT_MAX_SPECIFIED;
//
//		return ENTRIES_FOR_PAGE;
//	}
//	
//	private void updateDialog() {
//		updatePageInfo();
//		updateTable();
//	}
//
//	private void updateTable() {
//		dialog.getPanelQuestionList().getTable().unselectAll();
//
//		dialog.getPanelQuestionList().getTable().getTabModel().clearTable();
//
//		fillQuestionOnScreenList();
//		convertUserInputAndAddToTable();
//	}
//	
//	private void updatePageInfo() {
//		dialog.getPanelQuestionList().getTabPanel().getTfPage().setText(String.valueOf(currentPage + 1));
//		dialog.getPanelQuestionList().getTabPanel().getLblPagesTotal().setText(FROM_TEXT + countTotalPages());
//	}
//
//	private void fillQuestionOnScreenList() {
//		QuestionService questionService = new QuestionService();
//
//		questionsOnScreenList = questionService.getByNameSpecListAndId(getLimitStart(), getLimitMax(),
//				getQuestionTitleFromFilterUI(), getSetsFromFilterUI(), getIdFromFilterUI());
//	}
//
//	private String getQuestionTitleFromFilterUI() {
//		return dialog.getPanelQuestionList().getTfQuestionName().getText();
//	}
//
//	private Set<QuestionSet> getSetsFromFilterUI() {
//		String setsText = dialog.getPanelQuestionList().getTfSpecs().getText();
//		return parseSets(setsText);
//	}
//
//	private Long getIdFromFilterUI() {
//		String idText = dialog.getPanelQuestionList().getTfId().getText();
//		Long id = 0l;
//
//		if (!idText.isEmpty())
//			id = Long.parseLong(idText);
//
//		return id;
//	}
//
//	private int countTotalPages() {
//		QuestionService questionService = new QuestionService();
//
//		totalQuestions = questionService.countByNameSpecListAndId(getQuestionTitleFromFilterUI(), getSetsFromFilterUI(),
//				getIdFromFilterUI());
//
//		return this.totalPages = totalQuestions / ENTRIES_FOR_PAGE + 1;
//	}
//
//	private Set<QuestionSet> parseSets(String setsText) {
//		if (!setsText.isEmpty()) {
//			QuestionSetService setService = new QuestionSetService();
//			String[] setsTextList = setsText.trim().replaceAll(" ", "").split(",");
//
//			Set<QuestionSet> setsList = new HashSet<QuestionSet>();
//			for (int i = 0; i < setsTextList.length; i++)
//				setsList.addAll(setService.getByName(setsTextList[i]));
//
//			return setsList;
//		}
//		return null;
//
//	}
//
//	private void convertUserInputAndAddToTable() {
//		for (int i = 0; i < questionsOnScreenList.size(); i++) {
//			Question question = questionsOnScreenList.get(i);
//
//			Long id = question.getId();
//			String title = question.getTitle();
//			QuestionSet questionSet = question.getQuestionSet();
//
//			String setString = questionSet.getName();
//
//			Object[] row = { id, title, setString };
//
//			dialog.getPanelQuestionList().getTableModel().setRow(row, i);
//
//			dialog.getPanelQuestionList().getTableModel().update();
//		}
//	}
//
//	private void addBlankQuestion() {
//		Question q = new Question();
//		q.setAnswers(new HashSet<Answer>(AppConstants.MAX_ANSWERS_AMOUNT));
//		q.setQuestionSet(new QuestionSet());
//
//		questionsOnScreenList.add(q);
//	}
//
//	@Override
//	public void selectionChanged(int index) {
//		if (index >= questionsOnScreenList.size())
//			addBlankQuestion();
//		currentQuestion = questionsOnScreenList.get(index);
//	}
//
//	@Override
//	public void nextPage() {
//		if (currentPage + 1 < totalPages)
//			currentPage++;
//		updateDialog();
//	}
//
//	@Override
//	public void previousPage() {
//		if (currentPage > 0)
//			currentPage--;
//		updateDialog();
//	}
//
//	@Override
//	public void delete(int index) {
//		if (MessageBox.showConfirmQuestionDelete(dialog)) {
//			QuestionService service = new QuestionService();
//			service.delete(currentQuestion);
//		}
//		updateDialog();
//
//	}
//
//	@Override
//	public void goToPage(int page) {
//		if (page <= totalPages && page > 0)
//			currentPage = page - 1;
//
//		updateDialog();
//	}
//
//	@Override
//	public void addEntry(int index) {
////		DialogBuilder.showQuestionEditDialog(null);
////		dialog.dispose();
//		
//		 CardLayout cl = (CardLayout)(dialog.getPnlCards().getLayout());
//		 
//		 String panelNameToShow = dialog.getPanelOnScreenName() == QuestionListDialog.QUESTIONLISTNAME ? QuestionListDialog.QUESTIONEDITNAME : QuestionListDialog.QUESTIONLISTNAME;
//		 
//		 dialog.setPanelOnScreenName(panelNameToShow);
//		 
//	     cl.show(dialog.getPnlCards(), panelNameToShow);
//		
//	}
//
//	@Override
//	public void edit() {
//		DialogBuilder.showQuestionEditDialog(currentQuestion);
//		dialog.dispose();
//	}
//
//	@Override
//	public void save() {
//	}
//
//	@Override
//	public void cancel() {	
//	}

	@Override
	public void selectionChanged(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void previousPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToPage(int page) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEntry(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListeners() {
		// TODO Auto-generated method stub
		
	}
}