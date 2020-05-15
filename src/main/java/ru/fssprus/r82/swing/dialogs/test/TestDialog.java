package ru.fssprus.r82.swing.dialogs.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ru.fssprus.r82.swing.dialogs.CommonDialog;
import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.Utils;

/**
 * @author Chernyj Dmitry
 *
 */
public class TestDialog extends CommonDialog {
	private static final long serialVersionUID = 1355222097401941564L;
	private static final String SECTION = AppConstants.TEST_DIALOG;
	private static final String TITLE = AppConstants.TEST_TEXT;
	private static final String ICON = AppConstants.TEST_ICON;
	

	private static final String BTN_TO_NEXT_CAPTION = "К следующему";
	private static final String BTN_FINISH_CAPTION = "Закончить тест";
	private static final String BTN_NEXT_CAPTION = ">";
	private static final String BTN_PREVIOUS_CAPTION = "<";
	
	private static final int QUESTION_TEXT_SIDE_INDENT = 25;
	
	private static final int AMT_RAD_BUTTONS = 5;

	private JPanel pnlQuizzControll = new JPanel();
	private JPanel pnlQuestAndAnswers = new JPanel();
	private JPanel pnlAnswers = new JPanel();
	private JButton btnNextUnanswered = new JButton(BTN_TO_NEXT_CAPTION);
	private JButton btnFinish = new JButton(BTN_FINISH_CAPTION);

	private JLabel lblQuestionInfo = new JLabel();
	private JTextArea taQuestionText = new JTextArea();
	private ArrayList<JCheckBox> cbAnswers = new ArrayList<JCheckBox>(AMT_RAD_BUTTONS);

	private JPanel pnlDown = new JPanel();
	private JButton btnNext = new JButton(BTN_NEXT_CAPTION);
	private JButton btnPrevious = new JButton(BTN_PREVIOUS_CAPTION);
	private JLabel lblTimeLeftSec = new JLabel();

	private boolean isPaused = false;
	
	private JLabel lblImage = new JLabel();
	
	private JFrame mainFrame;

	public TestDialog(int width, int height, JFrame parent) {
		super(width, height, parent);
		
		mainFrame = parent;
		
		setMaximized();
	}
	
	private void setMaximized() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		this.setSize(screenWidth, screenHeight);
	}

	@Override
	public void layoutPanelTop() {
		ImageIcon emblem = new ImageIcon(getClass().getResource(ICON));
		super.layoutPanelTop(TITLE, emblem);
	}

	@Override
	public void init() {
		fillBgAnswers();
		super.init();
	}

	@Override
	protected void layoutDialog() {
		setFonts();

		layoutPanelQuizzControll();
		layoutPanelQuestAndAnswers();
		layoutPanelDown();

		addComponents();

		setVisible(true);
	}

	private void addComponents() {
		JPanel contentPanel = getContentPanel();
		
		contentPanel.add(pnlQuizzControll, BorderLayout.NORTH);

		contentPanel.add(pnlQuestAndAnswers, BorderLayout.CENTER);

		contentPanel.add(pnlDown, BorderLayout.SOUTH);
	}

	@Override
	protected String getSection() {
		return SECTION;
	}

	@Override
	protected String getTitleText() {
		return TITLE;
	}

	private void layoutPanelQuizzControll() {
		pnlQuizzControll.add(lblQuestionInfo);
		pnlQuizzControll.add(btnFinish);
		pnlQuizzControll.add(btnNextUnanswered);
	}

	private void layoutPanelDown() {
		btnPrevious.setEnabled(false);
		pnlDown.add(btnPrevious);
		pnlDown.add(lblTimeLeftSec);
		pnlDown.add(btnNext);
	}
	
	public int countWidth() {
		return this.getWidth() / 2 - QUESTION_TEXT_SIDE_INDENT;
	}

	private void layoutPanelQuestAndAnswers() {
		initTaQuestionText();
		initPanelAnswers();
		
		final int side = QUESTION_TEXT_SIDE_INDENT;
		
		JScrollPane scrollerQuestion = new JScrollPane(taQuestionText,
	            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane scrollerImage = new JScrollPane(lblImage,
	            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		Dimension dim = new Dimension(countWidth(), Utils.countTestDialogTaQuestionHeight(this.getHeight()));
		
		scrollerQuestion.setPreferredSize(dim);
		
		scrollerImage.setPreferredSize(dim);
		

		pnlQuestAndAnswers.setLayout(new GridBagLayout());
		
		pnlQuestAndAnswers.add(scrollerQuestion, new GridBagConstraints(
				0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, side, 0, 0), 0,0));
		
		pnlQuestAndAnswers.add(scrollerImage, new GridBagConstraints(
				1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, side), 0,0));
		
		pnlQuestAndAnswers.add(pnlAnswers, new GridBagConstraints(
				0, 1, GridBagConstraints.REMAINDER, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, side, 0, 0), 0,0));
		
	}
	
	private void initTaQuestionText() {
		taQuestionText.setEnabled(false);
		taQuestionText.setWrapStyleWord(true);
		taQuestionText.setLineWrap(true);
		taQuestionText.setDisabledTextColor(Color.BLACK);
	}
	
	private void initPanelAnswers() {

		pnlAnswers.setPreferredSize(
				new Dimension(this.getWidth(), Utils.countTestDialogPnlAnswersHeight(this.getHeight())));

		pnlAnswers.setLayout(new GridBagLayout());

		for (int i = 0; i < AMT_RAD_BUTTONS; i++) {
			pnlAnswers.add(cbAnswers.get(i), new GridBagConstraints(0, i, GridBagConstraints.REMAINDER, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
		}
	}

	private void setFonts() {
		Font fontHeader = AppConstants.TESTDIALOG_HEADER_FONT;
		Font fontQuestion = AppConstants.TESTDIALOG_QUESTION_FONT;
		Font fontItems = AppConstants.TESTDIALOG_ITEMS_FONT;

		lblQuestionInfo.setFont(fontHeader);
		taQuestionText.setFont(fontQuestion);
		for (int i = 0; i < AMT_RAD_BUTTONS; i++) {
			cbAnswers.get(i).setFont(fontItems);
			cbAnswers.get(i).setForeground(Color.BLACK);
		}
	}

	private void fillBgAnswers() {
		for (int i = 0; i < AMT_RAD_BUTTONS; i++) {
			JCheckBox jrb = new JCheckBox();
			cbAnswers.add(jrb);
		}
	}

	public ArrayList<JCheckBox> getCbAnswers() {
		return cbAnswers;
	}

	public void setRbAnswers(ArrayList<JCheckBox> cbAnswers) {
		this.cbAnswers = cbAnswers;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}

	public JButton getBtnPrevious() {
		return btnPrevious;
	}

	public void setBtnPrevious(JButton btnPrevious) {
		this.btnPrevious = btnPrevious;
	}

	public JLabel getLblTimeLeftSec() {
		return lblTimeLeftSec;
	}

	public void setLblTimeLeftSec(JLabel lblTimeLeftSec) {
		this.lblTimeLeftSec = lblTimeLeftSec;
	}

	public JButton getBtnNextUnanswered() {
		return btnNextUnanswered;
	}

	public void setBtnNextUnanswered(JButton btnNextUnanswered) {
		this.btnNextUnanswered = btnNextUnanswered;
	}

	public JButton getBtnFinish() {
		return btnFinish;
	}

	public void setBtnFinish(JButton btnFinish) {
		this.btnFinish = btnFinish;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public JLabel getLblQuestionInfo() {
		return lblQuestionInfo;
	}

	public void setLblQuestionInfo(JLabel lblQuestionInfo) {
		this.lblQuestionInfo = lblQuestionInfo;
	}

	public JTextArea getTaQuestionText() {
		return taQuestionText;
	}

	public void setTaQuestionText(JTextArea taQuestionText) {
		this.taQuestionText = taQuestionText;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public JLabel getLblImage() {
		return lblImage;
	}

	public void setLblImage(JLabel lblImage) {
		this.lblImage = lblImage;
	}
	
}