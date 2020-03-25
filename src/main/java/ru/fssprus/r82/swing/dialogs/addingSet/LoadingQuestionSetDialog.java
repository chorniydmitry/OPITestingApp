package ru.fssprus.r82.swing.dialogs.addingSet;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.swing.dialogs.DialogWithPassword;
import ru.fssprus.r82.swing.utils.JGreenButton;
import ru.fssprus.r82.utils.AppConstants;

public class LoadingQuestionSetDialog extends DialogWithPassword {
	private static final long serialVersionUID = -4114441914928348354L;

	private static final String SECTION = AppConstants.QUESTION_LOAD_DIALOG;
	private static final String TITLE = AppConstants.QUESTION_LOAD_TEXT;
	private static final String ICON = AppConstants.QUESTION_LOAD_ICON;

	private JLabel lblMsg = new JLabel(AppConstants.DIALOG_LOADING_QUEST_SET_ABOUT_INFO);

	private static final int TF_SIZE = 25;
	private static final String PNL_ADD_NEW_SET_TITLE = "Создание нового набора вопросов";
	private static final String LBL_NAMEOGNEWSET_CAPTION = "Название:";
	private static final String BTN_SAVENEWSET_CAPTION = "Сохранить";

	private static final String PNL_ADDQUESTIONSFROMFILE_TITLE = "Добавление вопросов в набор из файла";
	private static final String LBL_SPEC_NAME_CAPTION = "Название набора вопросов:";
	private static final String LBL_AMOUNTOFQUESTIONSINSET_CAPTION = "Вопросов в БД:";
	private static final String LBL_ADDQUESTIONS_CAPTION = "Добавить вопросы:";
	private static final String BTN_OPEN_CAPTION = "Открыть файл";
	private static final String BTN_LOAD_CAPTION = "Добавить";

	private static final String BTN_LOAD_TEMPLATE = "Открыть шаблон файла вопросов";

	private JPanel pnlSetEditing = new JPanel();

	private JPanel pnlAddNewSet = new JPanel();
	private JLabel lblNameOfNewSet = new JLabel(LBL_NAMEOGNEWSET_CAPTION);
	private JTextField tfNameOfNewTest = new JTextField(TF_SIZE);
	private JButton btnSaveNewSet = new JButton(BTN_SAVENEWSET_CAPTION);

	private JPanel pnlAddQuestionsFromFile = new JPanel();
	private JLabel lblSpecName = new JLabel(LBL_SPEC_NAME_CAPTION);
	private JComboBox<String> cbSpecName = new JComboBox<>();
	private JLabel lblTotalQuestionsInSet = new JLabel(LBL_AMOUNTOFQUESTIONSINSET_CAPTION);
	private JLabel lblTotalQuestionsInSetVal = new JLabel();
	private JLabel lblAddQuestions = new JLabel(LBL_ADDQUESTIONS_CAPTION);
	private JButton btnOpenTextFile = new JGreenButton(BTN_OPEN_CAPTION);
	private JTextField tfFilePath = new JTextField(TF_SIZE);
	private JButton btnLoadQuestionsSet = new JGreenButton(BTN_LOAD_CAPTION);

	private JButton btnLoadSetFileTemplate = new JGreenButton(BTN_LOAD_TEMPLATE);

	
	private void layoutPnlSetEditing() {
		layoutPnlAddNewSet();
		layoutPnlAddQuestionsFromFile();
		
		pnlSetEditing.setLayout(new BorderLayout());
		pnlSetEditing.add(pnlAddNewSet, BorderLayout.NORTH);
		pnlSetEditing.add(pnlAddQuestionsFromFile, BorderLayout.SOUTH);
	}
	
	private void layoutPnlAddNewSet() {
		pnlAddNewSet.setBorder(new TitledBorder(PNL_ADD_NEW_SET_TITLE));
		pnlAddNewSet.add(lblNameOfNewSet);
		pnlAddNewSet.add(tfNameOfNewTest);
		pnlAddNewSet.add(btnSaveNewSet);
	}

	private void layoutPnlAddQuestionsFromFile() {
		pnlAddQuestionsFromFile.setBorder(new TitledBorder(PNL_ADDQUESTIONSFROMFILE_TITLE));
		pnlAddQuestionsFromFile.setLayout(new GridBagLayout());

		// 1 row
		pnlAddQuestionsFromFile.add(lblSpecName, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		pnlAddQuestionsFromFile.add(cbSpecName, new GridBagConstraints(1, 0, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		pnlAddQuestionsFromFile.add(lblTotalQuestionsInSet, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		pnlAddQuestionsFromFile.add(lblTotalQuestionsInSetVal, new GridBagConstraints(3, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		// 2 row
		pnlAddQuestionsFromFile.add(lblAddQuestions, new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		// 3 row
		pnlAddQuestionsFromFile.add(btnOpenTextFile, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		pnlAddQuestionsFromFile.add(tfFilePath, new GridBagConstraints(1, 2, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		pnlAddQuestionsFromFile.add(btnLoadQuestionsSet, new GridBagConstraints(2, 2, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

	}

	public LoadingQuestionSetDialog(int width, int height, JFrame parent) {
		super(width, height, parent);
	}

	@Override
	public void layoutPanelTop() {
		ImageIcon emblem = new ImageIcon(getClass().getResource(ICON));
		super.layoutPanelTop(TITLE, emblem);
	}

	@Override
	public void init() {
		initComponents();
		super.init();
	}

	private void initComponents() {
		initTfSpecNames();
		tfFilePath.setEditable(false);
	}

	@Override
	protected String getSection() {
		return SECTION;
	}

	@Override
	protected String getTitleText() {
		return TITLE;
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

	private void initTfSpecNames() {
		QuestionSetService setService = new QuestionSetService();

		ArrayList<String> keywords = new ArrayList<String>();
		setService.getAll().forEach((n) -> keywords.add(n.getName()));

		keywords.forEach((n) -> cbSpecName.addItem(n));
	}

	public JButton getBtnLoadQuestionsSet() {
		return btnLoadQuestionsSet;
	}

	public void setBtnLoadQuestionsSet(JButton btnLoadQuestionsSet) {
		this.btnLoadQuestionsSet = btnLoadQuestionsSet;
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

	public JComboBox<String> getCbSpecName() {
		return cbSpecName;
	}

	public void setCbSpecName(JComboBox<String> cbSpecName) {
		this.cbSpecName = cbSpecName;
	}
}
