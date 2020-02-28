package ru.fssprus.r82.swing.dialogs.testCreator;

import java.util.List;

import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.service.TestService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.table.TablePanelController;
import ru.fssprus.r82.swing.table.UpdatableController;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */

public class TestConstructorController extends CommonController<TestConstructorDiaolg> implements UpdatableController {
	private static final int ENTRIES_FOR_PAGE = AppConstants.TABLE_ROWS_LIMIT;
	private int currentPage;
	private int totalPages;

	public TestConstructorController(TestConstructorDiaolg dialog) {
		super(dialog);
		
		TablePanelController tablePanelController = new TablePanelController(dialog.getTblList());
		tablePanelController.setSubscriber(this);
		
		loadExistingTests();
	}
	
	private void addTestToTable(List<Test> tests) {
		for (Test test : tests) {
			Object[] row = {0, test.getName(), test.getTestTimeSec(), test.getAmountOfQuestions(), test.isActive()};
			dialog.getTblList().getTable().getTabModel().addRow(row);
		}
		
		dialog.getTblList().getTable().getTabModel().update();
	}
	
	private void loadExistingTests() {
		TestService testService = new TestService();
		List<Test> tests = testService.getAll(0, 100);
		addTestToTable(tests);
		
//		TestService testServ = new TestService();
//
//		Test test = testServ.getById(1L);
//		
//		Set<TestSet> testSets = test.getTestSets();
//		
//		for (TestSet testSet : testSets) {
//			System.out.println(testSet.getTest().getName() + " " + testSet.getQuestionsAmount() + " " + testSet.getQuestionSet().getName());
//		}
//		
//		System.out.println(test.getName());
	}

	@Override
	protected void setListeners() {
		
	}

	@Override
	public void edit(int index) {
		System.out.println("EDIT PRESSED");
		
	}

	@Override
	public void nextPage() {
		System.out.println("NEXT PAGE PRESSED");
		
	}

	@Override
	public void previousPage() {
		System.out.println("PREVIOUS PAGE PRESSED");
		
	}

	@Override
	public void delete(int index) {
		System.out.println("DELETE PRESSED");
		
	}

	@Override
	public void goToPage(int page) {
		System.out.println("GO TO PAGE PRESSED");
		
	}
}
