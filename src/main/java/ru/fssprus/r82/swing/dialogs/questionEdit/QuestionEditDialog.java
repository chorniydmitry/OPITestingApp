package ru.fssprus.r82.swing.dialogs.questionEdit;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.swing.dialogs.CommonDialog;
import ru.fssprus.r82.swing.utils.JGreenButton;
import ru.fssprus.r82.utils.AppConstants;

public class QuestionEditDialog extends CommonDialog{
	
	private static final long serialVersionUID = -5920884439809017250L;
	public static final String BTN_SAVE_CAPTION_RU = "Сохранить";
	public static final String BTN_CANCEL_CAPTION_RU = "Отменить и выйти";
	public static final String LBL_SPECIFICATION_CAPTION_RU = "Набор вопросов";
	private static final String ANSWER_TEXT = "Ответ ";
	
	private ArrayList<JTextField> tfAnsList = new ArrayList<>();
	private ArrayList<JLabel> lblAnsList = new ArrayList<>();
	private ArrayList<JCheckBox> cbAnsList = new ArrayList<>();
	
	private JButton btnSaveQuestion = new JGreenButton(BTN_SAVE_CAPTION_RU);
	private JButton btnCancel = new JGreenButton(BTN_CANCEL_CAPTION_RU);

	private JLabel lblSpecName = new JLabel(LBL_SPECIFICATION_CAPTION_RU);
	private JComboBox<String> cbSpecNames = new JComboBox<>();
	
	private JTextArea taQuestion = new JTextArea();
	
	private JPanel pnlQuestionEdit = new JPanel();
	
	public QuestionEditDialog(int width, int height, JFrame parent) {
		super(width, height, parent);
	}
	
	private void layoutPanelQuestionEdit() {
		pnlQuestionEdit.setLayout(new GridBagLayout());

		for (int i = 0; i < AppConstants.MAX_ANSWERS_AMOUNT; i++) {
			lblAnsList.add(new JLabel(ANSWER_TEXT + (i + 1)));
			tfAnsList.add(new JTextField());
			cbAnsList.add(new JCheckBox());
		}

		taQuestion.setWrapStyleWord(true);
		taQuestion.setLineWrap(true);
		// taQuestion.setPreferredSize(new Dimension(this.getWidth(), 80));
		JScrollPane scrollPane = new JScrollPane(taQuestion);

		pnlQuestionEdit.setBorder(BorderFactory.createTitledBorder(AppConstants.QLDIALOG_PNL_QEDIT_BORDER_TITLE_RU));

		// gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill,
		// insets(top, left, botom, right), ipadx, ipady

		// 1st .. cbLevelsList.size() row
		pnlQuestionEdit.add(scrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		pnlQuestionEdit.add(scrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		//
		pnlQuestionEdit.add(lblSpecName, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		pnlQuestionEdit.add(cbSpecNames, new GridBagConstraints(1, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// cbLevelsList.size()+1 row
		for (int i = 0; i < AppConstants.MAX_ANSWERS_AMOUNT; i++) {
			pnlQuestionEdit.add(lblAnsList.get(i), new GridBagConstraints(0, 1 + 1 + i, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));

			pnlQuestionEdit.add(tfAnsList.get(i), new GridBagConstraints(1, 1 + 1 + i, 1, 1, 1, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

			pnlQuestionEdit.add(cbAnsList.get(i), new GridBagConstraints(2, 1 + 1 + i, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
		}

		// last row
		pnlQuestionEdit.add(btnSaveQuestion, new GridBagConstraints(1, 1 + 2 + AppConstants.MAX_ANSWERS_AMOUNT, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		pnlQuestionEdit.add(btnCancel, new GridBagConstraints(2, 1 + 2 + AppConstants.MAX_ANSWERS_AMOUNT, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		pnlQuestionEdit.setVisible(true);

	}
	
	
	public ArrayList<JTextField> getTfAnsList() {
		return tfAnsList;
	}

	public void setTfAnsList(ArrayList<JTextField> tfAnsList) {
		this.tfAnsList = tfAnsList;
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

	public JComboBox<String> getCbSpecNames() {
		return cbSpecNames;
	}

	public void setCbSpecNames(JComboBox<String> cbSpecNames) {
		this.cbSpecNames = cbSpecNames;
	}
	

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	@Override
	protected void layoutDialog() {
		layoutPanelQuestionEdit();
		
		this.add(pnlQuestionEdit);
	}


	@Override
	protected String getSection() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected String getTitleText() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void layoutPanelTop() {
		// TODO Auto-generated method stub
		
	}

}
