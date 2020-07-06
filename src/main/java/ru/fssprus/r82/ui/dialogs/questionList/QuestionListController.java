package ru.fssprus.r82.ui.dialogs.questionList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTextField;

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
	private static final String FROM_TEXT = " из ";
	private static final int ENTRIES_FOR_PAGE = AppConstants.TABLE_ROWS_LIMIT;
	private int currentPage;
	private int totalPages;

	private List<Question> questionsOnScreenList;

	private Question currentQuestion = null;

	private int totalQuestions;
	
	private Map<String, String> filters;

	public QuestionListController(QuestionListDialog dialog, Map<String, String> filters) {
		super(dialog);

		TablePanelController tablePanelController = new TablePanelController(dialog.getTabPanel());
		tablePanelController.setSubscriber(this);
		
		dialog.getTable().setColumnMultiline(1, true);

		this.filters = filters;
		
		updateDialog();
		
		mapToFields(filters);
	}
	
	
	private void mapToFields(Map<String, String> filters) {
		if(filters == null)
			return;
		
		for(Entry<String, String> entry : filters.entrySet()) {
			
			if(entry.getKey().equals("page"))
				if(entry.getValue() != null && !entry.getValue().isEmpty())
					goToPage(Integer.parseInt(entry.getValue()));
	
			for(int i = 0; i < dialog.getPnlFilter().getComponentCount(); i++) {
				String filterName = dialog.getPnlFilter().getComponent(i).getName();
				if(filterName == null)
					continue;
				JTextField currTf = null;
				if(entry.getKey().equals(filterName))
					if(dialog.getPnlFilter().getComponent(i) instanceof JTextField) {
						currTf  = (JTextField)dialog.getPnlFilter().getComponent(i);
						currTf.setText(entry.getValue());
					}
			}
		
		}
		
		
	}

	@Override
	protected void setListeners() {
		dialog.getBtnFilter().addActionListener(listener -> updateDialog());
		dialog.getBtnClearFilters().addActionListener(listener -> doClearFiltersAction());
	}


	private void doClearFiltersAction() {
		dialog.getTable().unselectAll();

		dialog.getTfId().setText(null);
		dialog.getTfQuestionName().setText(null);
		dialog.getTfSpecs().setText(null);

		updateDialog();
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

		return this.totalPages = totalQuestions / ENTRIES_FOR_PAGE;
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
	}
	
	private void updateFilters() {
		if(filters == null)
			filters = new HashMap<String, String>();
		
		if(!dialog.getTabPanel().getTfPage().getText().isEmpty())
			filters.put("page", dialog.getTabPanel().getTfPage().getText());
		
		if(dialog.getTable().getSelectedRow() > 0)
			filters.put("row", String.valueOf(dialog.getTable().getSelectedRow()));
		
		for(int i = 0; i < dialog.getPnlFilter().getComponentCount(); i++) {
			if(dialog.getPnlFilter().getComponent(i).getName() == null)
				continue;
			
			JTextField currTf = null;
			if(dialog.getPnlFilter().getComponent(i) instanceof JTextField)
				currTf = (JTextField) dialog.getPnlFilter().getComponent(i);
			
			filters.put(dialog.getPnlFilter().getComponent(i).getName(),
						currTf.getText());
			}
	}

	@Override
	public void selectionChanged(int index) {
		if (index >= questionsOnScreenList.size())
			addBlankQuestion();
		currentQuestion = questionsOnScreenList.get(index);
	}

	@Override
	public void nextPage() {
		if (currentPage + 1 < totalPages)
			currentPage++;
		updateDialog();
	}

	@Override
	public void previousPage() {
		if (currentPage > 0)
			currentPage--;
		updateDialog();
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

	@Override
	public void addEntry(int index) {
		updateFilters();
		DialogBuilder.showQuestionEditDialog(null, filters);
		dialog.dispose();
		
	}

	@Override
	public void edit() {
		updateFilters();
		DialogBuilder.showQuestionEditDialog(currentQuestion, filters);
		dialog.dispose();
	}

	@Override
	public void save() {
	}

	@Override
	public void cancel() {	
	}
}