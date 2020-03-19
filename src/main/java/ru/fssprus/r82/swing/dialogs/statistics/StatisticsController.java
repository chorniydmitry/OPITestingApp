
package ru.fssprus.r82.swing.dialogs.statistics;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JTable;

import ru.fssprus.r82.dao.ResultDao;
import ru.fssprus.r82.dao.impl.ResultDatabaseDao;
import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Result;
import ru.fssprus.r82.entity.User;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.service.ResultService;
import ru.fssprus.r82.service.UserService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.table.TablePanelController;
import ru.fssprus.r82.swing.table.UpdatableController;
import ru.fssprus.r82.swing.utils.MessageBox;
import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.MarkCounter;
import ru.fssprus.r82.utils.TimeUtils;
import ru.fssprus.r82.utils.Utils;
import ru.fssprus.r82.utils.spreadsheet.SpreadSheetAdapter;
import ru.fssprus.r82.utils.spreadsheet.SpreadsheetFileChooser;

/**
 * @author Chernyj Dmitry
 *
 */
public class StatisticsController extends CommonController<StatisticsDialog> implements UpdatableController {
	private static final int ENTRIES_FOR_PAGE = AppConstants.TABLE_ROWS_LIMIT;
	private static final String FROM_TEXT = " из ";
	private static final String COMMON_TEXT = "Общие";
	private int currentPage;
	private int totalPages;

	private List<Result> testsOnScreenList;
	private List<Result> totalEntriesList;

	public StatisticsController(StatisticsDialog dialog) {
		super(dialog);
		initCbs();
		dialog.getTabPanel().getBtnAdd().setEnabled(false);

		TablePanelController tablePanelController = new TablePanelController(dialog.getTabPanel());
		tablePanelController.setSubscriber(this);

		updateTableAction();
	}

	@Override
	protected void setListeners() {
		dialog.getBtnClearFilters().addActionListener(listener -> doClearFiltersAction());
		dialog.getBtnFilter().addActionListener(listener -> updateTableAction());
		dialog.getBtnPrint().addActionListener(listener -> doPrintAction());
		dialog.getBtnExportSheet().addActionListener(listener -> doExportSheetAction());
	}

	private void doExportSheetAction() {
		SpreadsheetFileChooser chooser = new SpreadsheetFileChooser();
		File fileToSave = chooser.selectSpreadSheetFileToSave();
		SpreadSheetAdapter adapter = new SpreadSheetAdapter(fileToSave);
		
		totalEntriesList = getByFilter(0,0);
		dialog.getTableModel().clearTable();
		convertAndAddToTable(totalEntriesList);
		
		try {
			adapter.exportTable(fileToSave, dialog.getTable());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		updateTableAction();
	}

	private void doPrintAction() {
		try {

			totalEntriesList = getByFilter(0, 0);
			dialog.getTableModel().clearTable();
			convertAndAddToTable(totalEntriesList);

			PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
			set.add(OrientationRequested.LANDSCAPE);
			MessageFormat header = new MessageFormat("Статистика тестирования");
			MessageFormat footer = new MessageFormat("Стриница {0,number,integer}");
			dialog.getTable().print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, false);

			
		} catch (PrinterException e) {
			e.printStackTrace();
		}
		
		updateTableAction();
	}

	private void initCbs() {
		initCbMarks();
		initCbSets();
	}

	private void initCbMarks() {
		dialog.getCbMarks().addItem(null);
		for (String mark : MarkCounter.getAllMarksWords())
			dialog.getCbMarks().addItem(mark);
	}

	private void initCbSets() {
		QuestionSetService service = new QuestionSetService();
		List<QuestionSet> setList = service.getAll();

		dialog.getCbSets().addItem(null);

		for (QuestionSet set : setList) {
			if (set.getName().toUpperCase().equals(COMMON_TEXT.toUpperCase()))
				continue;
			dialog.getCbSets().addItem(set.getName());
		}
	}

	private void doFilterAction() {
		testsOnScreenList = getByFilter(currentPage * ENTRIES_FOR_PAGE, ENTRIES_FOR_PAGE);
	}

	private Set<User> getUsers() {
		UserService userService = new UserService();
		Set<User> users = null;
		if (!dialog.getTfSurNamLast().getText().isEmpty())
			users = new HashSet<>(userService.getBySurname(dialog.getTfSurNamLast().getText()));

		return users;
	}

	private Set<QuestionSet> getSets() {
		QuestionSetService setService = new QuestionSetService();

		Set<QuestionSet> sets = null;
		if (dialog.getCbSets().getSelectedIndex() != -1) {
			sets = new HashSet<>(setService.getByName(dialog.getCbSets().getSelectedItem().toString()));
		}

		return sets;
	}

	private Date getDateMore() {
		return dialog.getDpDateMore().getDate();
	}

	private Date getDateLess() {
		return dialog.getDpDateLess().getDate();
	}

	private String getResult() {
		String result = null;
		if (dialog.getCbMarks().getSelectedIndex() != -1)
			result = dialog.getCbMarks().getSelectedItem().toString();

		return result;
	}

	private int getScoreMore() {
		String scoreMoreText = dialog.getTfScoreMore().getText();
		return Utils.isNumeric(scoreMoreText) ? Integer.parseInt(scoreMoreText) : 0;
	}

	private int getScoreLess() {
		String scoreLessText = dialog.getTfScoreLess().getText();
		return Utils.isNumeric(scoreLessText) ? Integer.parseInt(scoreLessText) : 0;
	}

	private List<Result> getByFilter(int start, int max) {
		ResultService resultService = new ResultService();

		if (start != 0 || max != 0) {
			return resultService.getByUserQuestionSetAndDate(start, max, getUsers(), getSets(),
					getDateMore(), getDateLess(), getResult(), getScoreMore(), getScoreLess());
			
			
		} else {
			return resultService.getByUserQuestionSetAndDate(getUsers(), getSets(), getDateMore(),
					getDateLess(), getResult(), getScoreMore(), getScoreLess());
		}
	}

	private void doClearFiltersAction() {

		dialog.getTfSurNamLast().setText(null);
		dialog.getCbSets().setSelectedIndex(0);
		dialog.getCbMarks().setSelectedIndex(0);
		dialog.getDpDateLess().clear();
		dialog.getDpDateMore().clear();
		dialog.getTfScoreLess().setText(null);
		dialog.getTfScoreMore().setText(null);

		updateTableAction();
	}

	public void convertAndAddToTable(List<Result> tests) {
		for (int i = 0; i < tests.size(); i++) {
			Result test = tests.get(i);

			String userName = test.getUser().getSurname() + " " + test.getUser().getName() + " "
					+ test.getUser().getSecondName();
			String set = test.getQuestionSet().getName();

			Date testDate = test.getDate();

			String date = AppConstants.STATDIALOG_TABLE_DATE_FORMAT.format(testDate);

			int testSecs = test.getTestingTime();

			String testTime = String.valueOf(TimeUtils.stringTimes(testSecs));

			String corrects = String.valueOf(test.getCorrectAnswers());
			String percent = String.valueOf(test.getScore());
			String result = test.getResult();

			Object[] row = { i + 1, userName, set, date, testTime, corrects, percent, result };

			dialog.getTableModel().setRow(row, i);
			dialog.getTableModel().setRowColor(i,
					MarkCounter.countInColors(test.getTotalQuestions(), test.getCorrectAnswers()));

			dialog.getTableModel().update();
		}
	}

	private void updateTableAction() {
		dialog.getTable().unselectAll();
		dialog.getTableModel().clearTable();

		doFilterAction();

		dialog.getTabPanel().getTfPage().setText(String.valueOf(currentPage + 1));
		dialog.getTabPanel().getLblPagesTotal().setText(FROM_TEXT + countTotalPages());

		convertAndAddToTable(testsOnScreenList);

	}

	private int countTotalPages() {
		ResultDao testDao = new ResultDatabaseDao();
		this.totalPages = testDao.countByUserQuestionSetAndDate(getUsers(), getSets(),
				getDateMore(), getDateLess(), getResult(), getScoreMore(), getScoreLess()) / ENTRIES_FOR_PAGE + 1;
		
		return totalPages;
	}

	@Override
	public void selectionChanged(int index) {
	}

	@Override
	public void nextPage() {
		if (currentPage + 1 < totalPages)
			currentPage++;
		updateTableAction();
	}

	@Override
	public void previousPage() {
		if (currentPage > 0)
			currentPage--;
		updateTableAction();
	}

	@Override
	public void delete(int index) {
		if (MessageBox.showConfirmQuestionDelete(dialog)) {
			ResultService service = new ResultService();
			service.delete(testsOnScreenList.get(index));
		}
		updateTableAction();

	}

	@Override
	public void goToPage(int page) {
		if (page <= totalPages && page > 0)
			currentPage = page - 1;

		updateTableAction();
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
}
