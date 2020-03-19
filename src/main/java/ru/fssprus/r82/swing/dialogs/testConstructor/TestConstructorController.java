package ru.fssprus.r82.swing.dialogs.testConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

import ru.fssprus.r82.entity.QuestionSet;
import ru.fssprus.r82.entity.Test;
import ru.fssprus.r82.entity.TestSet;
import ru.fssprus.r82.service.QuestionService;
import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.service.TestService;
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.table.TablePanelController;
import ru.fssprus.r82.swing.table.UpdatableController;
import ru.fssprus.r82.swing.utils.MessageBox;

/**
 * @author Chernyj Dmitry
 *
 */

public class TestConstructorController extends CommonController<TestConstructorDiaolg> implements UpdatableController {
	private ArrayList<Test> testsOnScreen = new ArrayList<>();
	private Test currentTest;
	private boolean isReadyForSaving = false;
	
	private static final String EDITING_MESSAGE = "<<редактируется>>";

	public TestConstructorController(TestConstructorDiaolg dialog) {
		super(dialog);
		setEditing(false);
		subscribeToTableController();
		fillCbsWithAvailibleSets();
		loadExistingTests();
	}

	private void subscribeToTableController() {
		TablePanelController tablePanelController = new TablePanelController(dialog.getTblList());
		tablePanelController.setSubscriber(this);
	}

	private void fillCbsWithAvailibleSets() {
		QuestionSetService setService = new QuestionSetService();
		List<QuestionSet> questionSets = setService.getAll();

		for (int i = 0; i < dialog.getCbsSetNamesList().size(); i++) {
			dialog.getCbsSetNamesList().get(i).addItem(new String());
			for (QuestionSet questionSet : questionSets) {
				dialog.getCbsSetNamesList().get(i).addItem(new String(questionSet.getName()));
			}
		}
	}

	private void loadExistingTests() {
		dialog.getTblList().clearTable();
		TestService testService = new TestService();
		List<Test> tests = testService.getAll(0, 100);
		testsOnScreen = new ArrayList<Test>(tests);

		addTestToTable(tests);
	}

	private void addTestToTable(List<Test> tests) {
		for (Test test : tests) {
			Object[] row = { tests.indexOf(test) + 1, test.getName(), test.getTestTimeSec(),
					test.getAmountOfQuestions(), test.isActive() };
			dialog.getTblList().addRow(row);
		}

		dialog.getTblList().update();
	}

	private void doChangeSelectionAction(int index) {
		currentTest = testsOnScreen.get(index);

		clearLowerPanel();

		List<TestSet> testSets = new ArrayList<>(testsOnScreen.get(index).getTestSets());
		for (TestSet testSet : testSets) {
			String questionSetName = testSet.getQuestionSet().getName();
			int amountOfQuestions = testSet.getQuestionsAmount();
			int i = testSets.indexOf(testSet);
			int maxForSet = getAmountForSet(testSet.getQuestionSet());
			
			dialog.changeSpinnerMax(dialog.getSpnsSetAmountOfQuestionList().get(i), maxForSet);
			dialog.getSpnsSetAmountOfQuestionList().get(i).setValue(amountOfQuestions);
			dialog.getCbsSetNamesList().get(i).setSelectedItem(questionSetName);
		}
	
		dialog.getTfTestName().setText(currentTest.getName());
		dialog.getSpnQuestionsAmount().setValue(currentTest.getAmountOfQuestions());
		dialog.getSpnTestTime().setValue(currentTest.getTestTimeSec());
		dialog.getCbTestIsActive().setSelected(currentTest.isActive());
	}
	
	private int getAmountForSet(QuestionSet qSet) {
		QuestionService qServ = new QuestionService();
		return qServ.getAmountByQuestionSet(qSet);
	}

	private void clearLowerPanel() {
		for (int i = 0; i < TestConstructorDiaolg.MAXIMUM_OF_SETS; i++) {
			dialog.getSpnsSetAmountOfQuestionList().get(i).setValue(0);
			dialog.getCbsSetNamesList().get(i).setSelectedIndex(0);
		}
		dialog.getTfTestName().setText(null);
		dialog.getSpnQuestionsAmount().setValue(0);
		dialog.getSpnTestTime().setValue(0);
		dialog.getCbTestIsActive().setSelected(false);

	}

	private void addNewTest() {
		currentTest = new Test();
		testsOnScreen.add(currentTest);

		currentTest.setTestSets(new HashSet<TestSet>());
		
		for(int i = 1; i < dialog.getTblList().getColumnCount() - 1 ; i++) {
			dialog.getTblList().setValueAt(EDITING_MESSAGE, testsOnScreen.size()-1, i);
		}
	}

	private void setEditing(boolean isEditing) {
		for (int i = 0; i < TestConstructorDiaolg.MAXIMUM_OF_SETS; i++) {
			dialog.getSpnsSetAmountOfQuestionList().get(i).setEnabled(isEditing);
			dialog.getCbsSetNamesList().get(i).setEnabled(isEditing);
		}
		dialog.getSpnQuestionsAmount().setEnabled(isEditing);
		dialog.getTfTestName().setEnabled(isEditing);
		dialog.getSpnTestTime().setEnabled(isEditing);
		dialog.getCbTestIsActive().setEnabled(isEditing);
		dialog.getBtnSave().setEnabled(true);
	}

	private void doComboboxValueChangedAction(int index) {
		QuestionSetService qsService = new QuestionSetService();
		QuestionSet qSet = qsService.getByName(String.valueOf(dialog.getCbsSetNamesList().get(index).getSelectedItem())).get(0);
		int amount = getAmountForSet(qSet);
		dialog.changeSpinnerMax(dialog.getSpnsSetAmountOfQuestionList().get(index), amount);
	}

	private void doCheckAction() {
		if(checkCurrentTest()) {
			dialog.getBtnSave().setEnabled(false);
			dialog.getTblList().getBtnEditAndSave().setEnabled(true);
		} else {
			
		}
	}

	private void saveCurrentTest() {
		TestService testServ = new TestService();

		if (testServ.getById(currentTest.getId()) == null) {
			testServ.add(currentTest);
		} else {
			testServ.update(currentTest);
		}
		dialog.getTblList().clearTable();
		setEditing(false);
		loadExistingTests();
		
	}

	private void updateCurrentTest() {
		currentTest.setName(dialog.getTfTestName().getText());
		currentTest.setTestTimeSec((Integer)dialog.getSpnTestTime().getValue());
		currentTest.setAmountOfQuestions((Integer)dialog.getSpnQuestionsAmount().getValue());
		currentTest.setActive(dialog.getCbTestIsActive().isSelected());

		Map<QuestionSet, Integer> onScreenTestSet = getTestSetDataOnScreen();
		currentTest.getTestSets().removeAll(currentTest.getTestSets());

		for (Map.Entry<QuestionSet, Integer> entry : onScreenTestSet.entrySet()) {

			TestSet newTestSet = new TestSet();
			newTestSet.setQuestionsAmount(entry.getValue());
			newTestSet.setQuestionSet(entry.getKey());
			newTestSet.setTest(currentTest);
			
			currentTest.getTestSets().add(newTestSet);
		}
	}
	
	private Map<QuestionSet, Integer> getTestSetDataOnScreen() {
		QuestionSetService qSetService = new QuestionSetService();
		Map<QuestionSet, Integer> retVal = new HashMap<>();

		for (int i = 0; i < TestConstructorDiaolg.MAXIMUM_OF_SETS; i++) {
			String qSetStr = String.valueOf(dialog.getCbsSetNamesList().get(i).getSelectedItem());
			int amount = (Integer)(dialog.getSpnsSetAmountOfQuestionList().get(i).getValue());
			if (qSetStr.isEmpty() || amount == 0)
				continue;
			QuestionSet questionSet = qSetService.getByName(qSetStr).get(0);

			retVal.put(questionSet, amount);
		}

		return retVal;
	}

	private boolean checkCurrentTest() {
		//TODO СДЕЛАТЬ ВАЛИДАЦИЮ
		return true;
	}

	@Override
	protected void setListeners() {
		for(JComboBox<String> cb : dialog.getCbsSetNamesList()) {
			cb.addItemListener(l-> doComboboxValueChangedAction(dialog.getCbsSetNamesList().indexOf(cb)));
		}
		dialog.getBtnSave().addActionListener(l -> doCheckAction());
	}

	@Override
	public void selectionChanged(int index) {
		doChangeSelectionAction(index);
	
	}

	@Override
	public void nextPage() {
		// unused
	}

	@Override
	public void previousPage() {
		// unused
	}

	@Override
	public void goToPage(int page) {
		// unused
	}

	@Override
	public void addEntry(int index) {
		setEditing(true);
		addNewTest();
	}

	@Override
	public void edit() {
		setEditing(true);
		
	}

	@Override
	public void save() {
		updateCurrentTest();
		saveCurrentTest();
		loadExistingTests();
		setEditing(false);
		clearLowerPanel();
	}

	@Override
	public void cancel() {
		loadExistingTests();
		setEditing(false);
		clearLowerPanel();
	}

	@Override
	public void delete(int index) {
		boolean userConfirmed = MessageBox.showConfirmQuestionDelete(dialog);
			if(userConfirmed) {
				TestService tServ = new TestService();
				tServ.delete(currentTest);
				loadExistingTests();
				setEditing(false);
				clearLowerPanel();
			}
	}
}
