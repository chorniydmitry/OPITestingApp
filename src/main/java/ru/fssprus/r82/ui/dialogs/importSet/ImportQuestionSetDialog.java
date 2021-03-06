package ru.fssprus.r82.ui.dialogs.importSet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ru.fssprus.r82.ui.dialogs.DialogWithPassword;
import ru.fssprus.r82.utils.AppConstants;

public class ImportQuestionSetDialog extends DialogWithPassword {
	private static final long serialVersionUID = -4114441914928348354L;

	private JLabel lblMsg = new JLabel(AppConstants.DIALOG_LOADING_QUEST_SET_ABOUT_INFO);

	private static final int TF_SIZE = 50;
	private static final String PNL_ADD_NEW_SET_TITLE = "Создание нового набора вопросов";
	private static final String LBL_NAMEOGNEWSET_CAPTION = "Название:";
	private static final String BTN_SAVENEWSET_CAPTION = "Сохранить";

	private static final String PNL_ADDQUESTIONSFROMFILE_TITLE = "Добавление вопросов в набор из файла";
	private static final String LBL_SPEC_NAME_CAPTION = "Название набора:";
	private static final String LBL_AMOUNTOFQUESTIONSINSET_CAPTION = "Вопросов в БД:";
	private static final String LBL_TIMESINTESTS_CAPTION = "Входит в тесты:";
	private static final String LBL_ADDQUESTIONS_CAPTION = "Добавить вопросы:";
	private static final String BTN_OPEN_CAPTION = "Открыть файл";
	private static final String BTN_LOAD_CAPTION = "Добавить";

	private static final String BTN_LOAD_TEMPLATE = "Сохранить шаблон файла вопросов";

	private static final String BTN_DELETE_SET_CAPTION = "Удалить";

	private JPanel pnlSetEditing = new JPanel();

	private JPanel pnlAddNewSet = new JPanel();
	private JLabel lblNameOfNewSet = new JLabel(LBL_NAMEOGNEWSET_CAPTION);
	private JTextField tfNameOfNewTest = new JTextField(TF_SIZE);
	private JButton btnSaveNewSet = new JButton(BTN_SAVENEWSET_CAPTION);

	private JPanel pnlAddQuestionsFromFile = new JPanel();
	private JLabel lblSpecName = new JLabel(LBL_SPEC_NAME_CAPTION);
	private JComboBox<String> cbSetName = new JComboBox<>();
	private JLabel lblTotalQuestionsInSet = new JLabel(LBL_AMOUNTOFQUESTIONSINSET_CAPTION);
	private JLabel lblTotalQuestionsInSetVal = new JLabel();
	private JLabel lblTimesInTest = new JLabel(LBL_TIMESINTESTS_CAPTION);
	private JLabel lblTimesInTestVal = new JLabel();

	private JLabel lblAddQuestions = new JLabel(LBL_ADDQUESTIONS_CAPTION);
	private JButton btnOpenTextFile = new JButton(BTN_OPEN_CAPTION);
	private JTextField tfFilePath = new JTextField();
	private JButton btnImportQuestionsSet = new JButton(BTN_LOAD_CAPTION);

	private JButton btnLoadSetFileTemplate = new JButton(BTN_LOAD_TEMPLATE);

	private JButton btnDeleteSet = new JButton(BTN_DELETE_SET_CAPTION);

	public ImportQuestionSetDialog(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
		initComponents();
	}

	private void layoutPnlSetEditing() {
		layoutPnlAddNewSet();
		layoutPnlAddQuestionsFromFile();

		pnlSetEditing.setLayout(new BorderLayout());
		pnlSetEditing.add(pnlAddNewSet, BorderLayout.NORTH);
		pnlSetEditing.add(pnlAddQuestionsFromFile, BorderLayout.SOUTH);
	}

	private void layoutPnlAddNewSet() {
		pnlAddNewSet.setBorder(new TitledBorder(PNL_ADD_NEW_SET_TITLE));
		pnlAddNewSet.setBackground(Color.LIGHT_GRAY);
		pnlAddNewSet.add(lblNameOfNewSet);
		pnlAddNewSet.add(tfNameOfNewTest);
		pnlAddNewSet.add(btnSaveNewSet);
	}

	private void layoutPnlAddQuestionsFromFile() {
		pnlAddQuestionsFromFile.setBorder(new TitledBorder(PNL_ADDQUESTIONSFROMFILE_TITLE));
		pnlAddQuestionsFromFile.setLayout(new GridBagLayout());

		// 1 row
		pnlAddQuestionsFromFile.add(lblSpecName, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 1, 1));

		pnlAddQuestionsFromFile.add(cbSetName, new GridBagConstraints(1, 0, 5, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 1, 1));

		pnlAddQuestionsFromFile.add(btnDeleteSet, new GridBagConstraints(7, 0, 5, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 1, 1));

		// 2 row
		pnlAddQuestionsFromFile.add(lblTotalQuestionsInSet, new GridBagConstraints(2, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 1, 1));

		pnlAddQuestionsFromFile.add(lblTotalQuestionsInSetVal, new GridBagConstraints(3, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 1, 1));

		pnlAddQuestionsFromFile.add(lblTimesInTest, new GridBagConstraints(4, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 1, 1));

		pnlAddQuestionsFromFile.add(lblTimesInTestVal, new GridBagConstraints(5, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 1, 1));

		// 3 row
		pnlAddQuestionsFromFile.add(lblAddQuestions, new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		// 4 row
		pnlAddQuestionsFromFile.add(btnOpenTextFile, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 1, 1));

		pnlAddQuestionsFromFile.add(tfFilePath, new GridBagConstraints(1, 3, 5, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 1, 1));

		pnlAddQuestionsFromFile.add(btnImportQuestionsSet, new GridBagConstraints(6, 3, 2, 1, 1, 1,
				GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 1, 1));

	}

	private void initComponents() {
		tfFilePath.setEditable(false);
		cbSetName.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	@Override
	protected void layoutDialog() {
		layoutPnlSetEditing();

		JPanel contentPanel = getContentPanel();
		contentPanel.setLayout(new BorderLayout());

		contentPanel.add(lblMsg, BorderLayout.NORTH);

		contentPanel.add(pnlSetEditing, BorderLayout.CENTER);

		contentPanel.add(btnLoadSetFileTemplate, BorderLayout.SOUTH);
	}

	public JButton getBtnImportQuestionsSet() {
		return btnImportQuestionsSet;
	}

	public void setBtnImportQuestionsSet(JButton btnImportQuestionsSet) {
		this.btnImportQuestionsSet = btnImportQuestionsSet;
	}

	public JButton getBtnOpenTextFile() {
		return btnOpenTextFile;
	}

	public void setBtnOpenTextFile(JButton btnOpenTextFile) {
		this.btnOpenTextFile = btnOpenTextFile;
	}

	public JTextField getTfFilePath() {
		return tfFilePath;
	}

	public void setTfFilePath(JTextField tfFilePath) {
		this.tfFilePath = tfFilePath;
	}

	public JComboBox<String> getCbSetName() {
		return cbSetName;
	}

	public void setCbSetName(JComboBox<String> cbSetName) {
		this.cbSetName = cbSetName;
	}

	public JTextField getTfNameOfNewTest() {
		return tfNameOfNewTest;
	}

	public void setTfNameOfNewTest(JTextField tfNameOfNewTest) {
		this.tfNameOfNewTest = tfNameOfNewTest;
	}

	public JButton getBtnSaveNewSet() {
		return btnSaveNewSet;
	}

	public void setBtnSaveNewSet(JButton btnSaveNewSet) {
		this.btnSaveNewSet = btnSaveNewSet;
	}

	public JButton getBtnLoadSetFileTemplate() {
		return btnLoadSetFileTemplate;
	}

	public void setBtnLoadSetFileTemplate(JButton btnLoadSetFileTemplate) {
		this.btnLoadSetFileTemplate = btnLoadSetFileTemplate;
	}

	public JLabel getLblTotalQuestionsInSetVal() {
		return lblTotalQuestionsInSetVal;
	}

	public void setLblTotalQuestionsInSetVal(JLabel lblTotalQuestionsInSetVal) {
		this.lblTotalQuestionsInSetVal = lblTotalQuestionsInSetVal;
	}

	public JLabel getLblTimesInTestVal() {
		return lblTimesInTestVal;
	}

	public void setLblTimesInTestVal(JLabel lblTimesInTestVal) {
		this.lblTimesInTestVal = lblTimesInTestVal;
	}

	public JButton getBtnDeleteSet() {
		return btnDeleteSet;
	}

	public void setBtnDeleteSet(JButton btnDeleteSet) {
		this.btnDeleteSet = btnDeleteSet;
	}
}
