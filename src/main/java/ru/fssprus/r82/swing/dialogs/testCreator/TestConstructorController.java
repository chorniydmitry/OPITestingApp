package ru.fssprus.r82.swing.dialogs.testCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.TestSet;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.service.TestService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.table.SetListTableModel;
import ru.fssprus.r82.swing.table.TablePanelController;
import ru.fssprus.r82.swing.table.UpdatableController;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */

public class TestConstructorController extends CommonController<TestConstructorDiaolg> implements UpdatableController {
	private static final int ENTRIES_FOR_PAGE = AppConstants.TABLE_ROWS_LIMIT;
	
	private ArrayList<Test> testsOnScreen = new ArrayList<>();
	private Test currentTest;
	
	private int currentPage;
	private int totalPages;

	public TestConstructorController(TestConstructorDiaolg dialog) {
		super(dialog);
		
		TablePanelController tablePanelController = new TablePanelController(dialog.getTblList());
		tablePanelController.setSubscriber(this);
		
		fillCbAvailibleSets();
		loadExistingTests();
	}
	
	private void addTestToTable(List<Test> tests) {
		for (Test test : tests) {
			Object[] row = {0, test.getName(), test.getTestTimeSec(), test.getAmountOfQuestions(), test.isActive()};
			dialog.getTblList().addRow(row);
		}
		
		dialog.getTblList().update();
	}
	
	
	private void fillCbAvailibleSets() {
		QuestionSetService setService = new QuestionSetService();
		List<QuestionSet> questionSets = setService.getAll();
		for (QuestionSet questionSet : questionSets) {
			dialog.getCbAvailibleSets().addItem(new String(questionSet.getName()));
		}
		
		
	}
	
	private void loadExistingTests() {
		TestService testService = new TestService();
		List<Test> tests = testService.getAll(0, 100);
		testsOnScreen = new ArrayList<Test>(tests);
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
	
	private void doTestSelectedAction(int index) {
		currentTest = testsOnScreen.get(index);
		
		dialog.getTblCurrent().clearTable();
		
		Set<TestSet> currentTestSets = currentTest.getTestSets();
		for (TestSet testSet : currentTestSets) {
			Object[] row = {testSet.getQuestionSet().getName(), testSet.getQuestionsAmount()};
			dialog.getTblCurrent().addRow(row);
		}
		
		dialog.getTblList().getTable().getTabModel().update();
		
		
	}

	@Override
	protected void setListeners() {
		
	}

	@Override
	public void edit(int index) {
		doTestSelectedAction(index);
		
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
