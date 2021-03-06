package ru.fssprus.r82.ui.dialogs.testConstructor;
/**
 * @author Chernyj Dmitry
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import ru.fssprus.r82.ui.dialogs.DialogWithPassword;
import ru.fssprus.r82.ui.table.TablePanel;
import ru.fssprus.r82.ui.table.TestListTableModel;
import ru.fssprus.r82.utils.AppConstants;

public class TestConstructorDiaolg extends DialogWithPassword {
	private static final long serialVersionUID = -691574072269035086L;

	private static final int SPINNERS_STEP = 1;

	private static final String BTN_SAVE_CAPTION = "Проверить корректность заполнения";

	private static final String PANEL_TESTTABLE_CAPTION = "Список доступных вопросов";
	private static final String PANEL_TESTEDIT_CAPTION = "Редактирование теста";

	private static final String LBL_TESTNAME_CAPTION = "Название теста:";
	private static final String LBL_QUESTIONAMOUNT_CAPTION = "Количество вопросов(общее):";
	private static final String LBL_TESTTIME_CAPTION = "Время на тест(сек.):";
	private static final String LBL_TESTISACTIVE_CAPTION = "Тест активен?:";

	private static final String LBL_QS_CAPTION = "Наборы вопросов, которые входят в тест:";
	private static final String LBL_AMOUNTFORQSET_CAPTION = "Количество вопросов на набор:";
	private static final String LBL_TOTAL_AMOUNT_FOR_SET_CAPTION = "Доступно:";

	public static final String LBL_TEST_IS_CORRECT_CAPTION = "Тест сформирован корретно";
	public static final String LBL_TEST_IS_NOT_CORRECT_CAPTION = "Ошибки в формировании теста";
	public static final String LBL_TEST_IS_NOT_CHECKED_CAPTION = "Тест еще не проверялся";

	public static final Color LBL_TEST_IS_CORRECT_COLOR = new Color(155, 255, 155);
	public static final Color LBL_TEST_IS_NOT_CORRECT_COLOR = new Color(255, 155, 155);
	public static final Color LBL_TEST_IS_NOT_CHECKED_COLOR = new Color(155, 155, 155);
	
	private static final int TABLE_LIST_HEIGHT = 275;
	private static final int PANEL_LOWER_HEIGHT = 300;
	private static final int PANEL_TESTEDIT_HEIGHT = 75;
	private static final int PANEL_SETLIST_HEIGHT = 275;
	private static final int BTN_SAVE_HEIGHT = 25;
	public static final int MAXIMUM_OF_SETS = 10;

	private TablePanel tblList;
	private JPanel pnlLower;
	private JPanel pnlSetList;
	private JPanel pnlTestEdit;

	private JLabel lblTestName = new JLabel(LBL_TESTNAME_CAPTION);
	private JLabel lblQuestionsAmount = new JLabel(LBL_QUESTIONAMOUNT_CAPTION);
	private JLabel lblTestTime = new JLabel(LBL_TESTTIME_CAPTION);
	private JLabel lblTestIsActive = new JLabel(LBL_TESTISACTIVE_CAPTION);

	private JTextField tfTestName = new JTextField(65);
	private JSpinner spnQuestionsAmount = new JSpinner();
	private JSpinner spnTestTime = new JSpinner();
	private JCheckBox cbTestIsActive = new JCheckBox();

	private JButton btnSave = new JButton(BTN_SAVE_CAPTION);

	private JLabel lblQS = new JLabel(LBL_QS_CAPTION);
	private JLabel lblAmountForQSet = new JLabel(LBL_AMOUNTFORQSET_CAPTION);
	private JLabel lblTotalAmontForQSet = new JLabel(LBL_TOTAL_AMOUNT_FOR_SET_CAPTION);
	
	private JLabel lblTestIsCorrect = new JLabel();

	private List<JComboBox<String>> cbsSetNamesList = new ArrayList<>(MAXIMUM_OF_SETS);
	private List<JSpinner> spnsSetAmountOfQuestionList = new ArrayList<>(MAXIMUM_OF_SETS);
	private List<JLabel> lblsSetMaxAmountList = new ArrayList<>(MAXIMUM_OF_SETS);

	public TestConstructorDiaolg(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
		
		//TODO: MOVE THESE ???
		
		initComponents();
		layoutTblList();
		layoutPnlTestEdit();
		layoutTableSetListComponents();
		layoutLowerPanel();
	}

	private void layoutLowerPanel() {
		pnlLower = new JPanel(new BorderLayout());
		pnlLower.setSize(new Dimension(getWidth(), PANEL_LOWER_HEIGHT));
		pnlLower.add(pnlTestEdit, BorderLayout.NORTH);
		pnlLower.add(pnlSetList, BorderLayout.SOUTH);
	}

	@Override
	protected void layoutDialog() {
		getContentPanel().add(tblList, BorderLayout.NORTH);
		getContentPanel().add(pnlLower, BorderLayout.SOUTH);
	}

	private void initComponents() {

		tblList = new TablePanel(true, false,
				new TestListTableModel(AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_WIDTHS_ARR,
						AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_CAPTIONS_ARR));

		pnlLower = new JPanel();

		pnlLower.setPreferredSize(new Dimension(getWidth(), PANEL_LOWER_HEIGHT));

		pnlSetList = new JPanel();

		pnlSetList.setPreferredSize(new Dimension(getWidth(), PANEL_SETLIST_HEIGHT));

		pnlTestEdit = new JPanel();

		btnSave.setPreferredSize(new Dimension(getWidth(), BTN_SAVE_HEIGHT));
		
		lblTestIsCorrect.setOpaque(true);
	}

	private void layoutTblList() {
		tblList.setBorder(BorderFactory.createTitledBorder(PANEL_TESTTABLE_CAPTION));

		tblList.setPreferredSize(new Dimension(getWidth(), TABLE_LIST_HEIGHT));

	}

	private void layoutPnlTestEdit() {
		spnQuestionsAmount = configureSpinner(0, AppConstants.VALID_TEST_QUESTIONS_AMOUNT_MAX, 0);
		spnTestTime = configureSpinner(0, AppConstants.VALID_TEST_TIME_MAX, 0);

		pnlTestEdit.setBorder(BorderFactory.createTitledBorder(PANEL_TESTEDIT_CAPTION));

		pnlTestEdit.setPreferredSize(new Dimension(getWidth(), PANEL_TESTEDIT_HEIGHT));

		pnlTestEdit.add(lblTestName);
		pnlTestEdit.add(tfTestName);
		pnlTestEdit.add(lblTestTime);
		pnlTestEdit.add(spnTestTime);
		pnlTestEdit.add(lblQuestionsAmount);
		pnlTestEdit.add(spnQuestionsAmount);
		pnlTestEdit.add(lblTestIsActive);
		pnlTestEdit.add(cbTestIsActive);

	}

	private void layoutTableSetListComponents() {
		pnlSetList.setLayout(new GridBagLayout());

		pnlSetList.add(lblQS, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));
		
		pnlSetList.add(lblAmountForQSet, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));
		
		pnlSetList.add(lblTotalAmontForQSet, new GridBagConstraints(4, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));

		for (int i = 1; i <= MAXIMUM_OF_SETS; i++) {
			cbsSetNamesList.add(new JComboBox<String>());
			spnsSetAmountOfQuestionList.add(configureSpinner(0, 0, 0));
			lblsSetMaxAmountList.add(new JLabel());

			pnlSetList.add(cbsSetNamesList.get(i-1), new GridBagConstraints(0, i, 3, 1, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));

			pnlSetList.add(spnsSetAmountOfQuestionList.get(i-1), new GridBagConstraints(3, i, 1, 1, 1, 1,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));
			
			pnlSetList.add(lblsSetMaxAmountList.get(i-1), new GridBagConstraints(4, i, 1, 1, 1, 1,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));
		}

		pnlSetList.add(btnSave, new GridBagConstraints(0, MAXIMUM_OF_SETS + 1, 3, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));
		
		pnlSetList.add(lblTestIsCorrect, new GridBagConstraints(3, MAXIMUM_OF_SETS + 1, 2, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 1, 1));

		pnlSetList.setVisible(true);
	}

	private JSpinner configureSpinner(int min, int max, int current) {
		SpinnerNumberModel numbers = new SpinnerNumberModel(current, min, max, SPINNERS_STEP);
		return new JSpinner(numbers);
	}

	public void changeSpinnerMax(JSpinner spinner, int maximum) {
		SpinnerNumberModel spinnerModel = (SpinnerNumberModel) spinner.getModel();
		spinnerModel.setMaximum(maximum);
	}

	public TablePanel getTblList() {
		return tblList;
	}

	public void setTblList(TablePanel tblList) {
		this.tblList = tblList;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public List<JComboBox<String>> getCbsSetNamesList() {
		return cbsSetNamesList;
	}

	public void setCbsSetNamesList(ArrayList<JComboBox<String>> cbsSetNamesList) {
		this.cbsSetNamesList = cbsSetNamesList;
	}

	public List<JSpinner> getSpnsSetAmountOfQuestionList() {
		return spnsSetAmountOfQuestionList;
	}

	public void setSpnsSetAmountOfQuestionList(ArrayList<JSpinner> spnsSetAmountOfQuestionList) {
		this.spnsSetAmountOfQuestionList = spnsSetAmountOfQuestionList;
	}

	public JTextField getTfTestName() {
		return tfTestName;
	}

	public void setTfTestName(JTextField tfTestName) {
		this.tfTestName = tfTestName;
	}

	public JSpinner getSpnQuestionsAmount() {
		return spnQuestionsAmount;
	}

	public void setSpnQuestionsAmount(JSpinner spnQuestionsAmount) {
		this.spnQuestionsAmount = spnQuestionsAmount;
	}

	public JSpinner getSpnTestTime() {
		return spnTestTime;
	}

	public void setSpnTestTime(JSpinner spnTestTime) {
		this.spnTestTime = spnTestTime;
	}

	public JCheckBox getCbTestIsActive() {
		return cbTestIsActive;
	}

	public void setCbTestIsActive(JCheckBox cbTestIsActive) {
		this.cbTestIsActive = cbTestIsActive;
	}

	public JLabel getLblTestIsCorrect() {
		return lblTestIsCorrect;
	}

	public void setLblTestIsCorrect(JLabel lblTestIsCorrect) {
		this.lblTestIsCorrect = lblTestIsCorrect;
	}

	public List<JLabel> getLblsSetMaxAmountList() {
		return lblsSetMaxAmountList;
	}

	public void setLblsSetMaxAmountList(List<JLabel> lblsSetMaxAmountList) {
		this.lblsSetMaxAmountList = lblsSetMaxAmountList;
	}
	
}
