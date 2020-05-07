package ru.fssprus.r82.swing.dialogs.questionEdit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ru.fssprus.r82.swing.dialogs.CommonDialog;
import ru.fssprus.r82.swing.utils.JGreenButton;
import ru.fssprus.r82.utils.AppConstants;

public class QuestionEditDialog extends CommonDialog {
	
	private static final long serialVersionUID = -5920884439809017250L;
	
	private static final String SECTION = AppConstants.QUESTION_EDIT_ICON;
	private static final String TITLE = AppConstants.QUESTION_EDIT_TEXT;
	private static final String ICON = AppConstants.QUESTION_EDIT_ICON;
	
	private static final String BTN_ADD_IMAGE_CAPTION = "Добавить изображение";
	private static final String BTN_SAVE_CAPTION = "Сохранить изменения";
	private static final String BTN_CANCEL_CAPTION = "Отменить и выйти";
	private static final String LBL_SPECIFICATION_CAPTION = "Входит в набор вопросов:";
	private static final String LBL_ANS_TEXT_CAPTION = "Формулировка ответа:";
	private static final String LBL_QUEST_TEXT_CAPTION = "Формулировка вопроса:";
	private static final String LBL_ANS_ISCORRECT_CAPTION = "Верный?";

	private static final String ANSWER_TEXT = "Ответ #";
	private static final int TA_ANS_COLUMNS = 60;
	private static final int TA_ANS_ROWS = 5;

	private static final int TA_QUEST_COLUMNS = 70;
	private static final int TA_QUEST_ROWS = 10;

	private JLabel lblQuestText = new JLabel(LBL_QUEST_TEXT_CAPTION);
	
	private JButton btnAddImage = new JGreenButton(BTN_ADD_IMAGE_CAPTION);
	
	private JTextField tfImageLink = new JTextField(50);

	private JLabel lblAnsText = new JLabel(LBL_ANS_TEXT_CAPTION);
	private JLabel lblAnsIsCorrect = new JLabel(LBL_ANS_ISCORRECT_CAPTION);

	private ArrayList<JLabel> lblAnsList = new ArrayList<>(AppConstants.MAX_ANSWERS_AMOUNT);
	private ArrayList<JTextArea> taAnsList = new ArrayList<>(AppConstants.MAX_ANSWERS_AMOUNT);
	private ArrayList<JCheckBox> cbAnsList = new ArrayList<>(AppConstants.MAX_ANSWERS_AMOUNT);

	private JButton btnSaveQuestion = new JGreenButton(BTN_SAVE_CAPTION);
	private JButton btnCancel = new JGreenButton(BTN_CANCEL_CAPTION);

	private JLabel lblAvailibleSetName = new JLabel(LBL_SPECIFICATION_CAPTION);
	private JComboBox<String> cbAvailibleSetNames = new JComboBox<>();

	private JTextArea taQuestion = new JTextArea(TA_QUEST_ROWS, TA_QUEST_COLUMNS);

	private JPanel pnlQuestionEdit = new JPanel();
	private JPanel pnlAnswers = new JPanel();
	private JPanel pnlButtons = new JPanel();

	public QuestionEditDialog(int width, int height, JFrame parent) {
		super(width, height, parent);
		layoutPanelAnswers();
		layoutPanelButtons();
		layoutPanelQuestionEdit();
	}
	

	private void layoutPanelButtons() {
		pnlButtons.setLayout(new GridLayout(1, 2, 2, 2));
		pnlButtons.add(btnCancel);
		pnlButtons.add(btnSaveQuestion);
	}
	
	private void layoutPanelAnswers() {
		pnlAnswers.setLayout(new GridBagLayout());

		pnlAnswers.add(lblAnsText, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		pnlAnswers.add(lblAnsIsCorrect, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		for (int i = 0; i < AppConstants.MAX_ANSWERS_AMOUNT; i++) {
			JTextArea ta = new JTextArea(TA_ANS_ROWS, TA_ANS_COLUMNS);
			ta.setWrapStyleWord(true);
			ta.setLineWrap(true);
			

			JScrollPane scrollPane = new JScrollPane(ta);
			taAnsList.add(ta);

			JLabel lbl = new JLabel(ANSWER_TEXT + String.valueOf(i + 1));
			lblAnsList.add(lbl);

			JCheckBox cb = new JCheckBox();
			cbAnsList.add(cb);

			pnlAnswers.add(lbl, new GridBagConstraints(0, i + 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
					GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 1, 1));

			pnlAnswers.add(scrollPane, new GridBagConstraints(1, i + 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
					GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));

			pnlAnswers.add(cb, new GridBagConstraints(2, i + 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
					GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 1, 1));

		}
	}

	private void layoutPanelQuestionEdit() {
		taQuestion.setWrapStyleWord(true);
		taQuestion.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(taQuestion);

		pnlQuestionEdit.setLayout(new GridBagLayout());

		pnlQuestionEdit.add(lblQuestText, new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 2, 0, 2), 1, 1));
		
		pnlQuestionEdit.add(btnAddImage, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 0, 2), 1, 1));
		
		pnlQuestionEdit.add(tfImageLink, new GridBagConstraints(1, 1, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 0, 2), 1, 1));

		pnlQuestionEdit.add(scrollPane, new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 2, 0, 2), 1, 1));

		pnlQuestionEdit.add(lblAvailibleSetName, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 2, 0, 2), 1, 1));

		pnlQuestionEdit.add(cbAvailibleSetNames, new GridBagConstraints(0, 4, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 0, 2), 1, 1));

		pnlQuestionEdit.add(pnlAnswers, new GridBagConstraints(0, 5, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 2, 0, 2), 1, 1));
		
		pnlQuestionEdit.add(pnlButtons, new GridBagConstraints(0, 6, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 2, 0, 2), 1, 1));

	}

	public ArrayList<JLabel> getLblAnsList() {
		return lblAnsList;
	}

	public void setLblAnsList(ArrayList<JLabel> lblAnsList) {
		this.lblAnsList = lblAnsList;
	}

	public ArrayList<JCheckBox> getCbAnsList() {
		return cbAnsList;
	}

	public void setCbAnsList(ArrayList<JCheckBox> cbAnsList) {
		this.cbAnsList = cbAnsList;
	}

	public JTextArea getTaQuestion() {
		return taQuestion;
	}

	public void setTaQuestion(JTextArea taQuestion) {
		this.taQuestion = taQuestion;
	}

	public JButton getBtnSaveQuestion() {
		return btnSaveQuestion;
	}

	public void setBtnSave(JButton btnSaveQuestion) {
		this.btnSaveQuestion = btnSaveQuestion;
	}

	public JComboBox<String> getCbAvailibleSetNames() {
		return cbAvailibleSetNames;
	}

	public void setCbAvailibleSetNames(JComboBox<String> cbAvailibleSetNames) {
		this.cbAvailibleSetNames = cbAvailibleSetNames;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	@Override
	protected void layoutDialog() {
		getContentPanel().add(pnlQuestionEdit);
	}

	public ArrayList<JTextArea> getTaAnsList() {
		return taAnsList;
	}

	public void setTaAnsList(ArrayList<JTextArea> taAnsList) {
		this.taAnsList = taAnsList;
	}
	
	public JButton getBtnAddImage() {
		return btnAddImage;
	}


	public void setBtnAddImage(JButton btnAddImage) {
		this.btnAddImage = btnAddImage;
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
	protected void layoutPanelTop() {
		ImageIcon emblem = new ImageIcon(getClass().getResource(ICON));
		super.layoutPanelTop(TITLE, emblem);
	}


	public JTextField getTfImageLink() {
		return tfImageLink;
	}


	public void setTfImageLink(JTextField tfImageLink) {
		this.tfImageLink = tfImageLink;
	}
	
}
