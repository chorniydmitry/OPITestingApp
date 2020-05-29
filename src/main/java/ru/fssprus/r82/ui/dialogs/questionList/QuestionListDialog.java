package ru.fssprus.r82.ui.dialogs.questionList;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ru.fssprus.r82.ui.dialogs.DialogWithPassword;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionListDialog extends DialogWithPassword {
	private static final long serialVersionUID = -8319908967500731744L;

	private static final int CARD_PADDING_WIDTH = 20;
	private static final int CARD_PADDING_HEIGHT = 70;

	public static final String QUESTIONEDITNAME = "panelQuestionEdit";
	public static final String QUESTIONLISTNAME = "panelQuestionList";

	private PanelQuestionList panelQuestionList = new PanelQuestionList();
	private PanelQuestionEdit panelQuestionEdit = new PanelQuestionEdit();

	private JPanel pnlCards = new JPanel();

	private String panelOnScreenName = QUESTIONLISTNAME;

	public QuestionListDialog(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
		setControllers();
	}

	private void setControllers() {
		System.out.println("setControllers");
		//new PanelQuestionEditController(this);
		new PanelQuestionListController(this);
	}
	
	@Override
	protected void layoutDialog() {
		System.out.println("layout");
		JPanel contentPanel = getContentPanel();

		pnlCards.setLayout(new CardLayout());

		int cardWidth = getContentPanel().getWidth() - CARD_PADDING_WIDTH;
		int cardHeight = getContentPane().getHeight() - CARD_PADDING_HEIGHT;
		Dimension cardDim = new Dimension(cardWidth, cardHeight);

		panelQuestionEdit.setPreferredSize(cardDim);
		panelQuestionList.setPreferredSize(cardDim);

		pnlCards.add(panelQuestionList, QUESTIONLISTNAME);
		pnlCards.add(panelQuestionEdit, QUESTIONEDITNAME);

		contentPanel.add(pnlCards);
	}

	public PanelQuestionList getPanelQuestionList() {
		return panelQuestionList;
	}

	public void setPanelQuestionList(PanelQuestionList panelQuestionList) {
		this.panelQuestionList = panelQuestionList;
	}

	public PanelQuestionEdit getPanelQuestionEdit() {
		return panelQuestionEdit;
	}

	public void setPanelQuestionEdit(PanelQuestionEdit panelQuestionEdit) {
		this.panelQuestionEdit = panelQuestionEdit;
	}

	public JPanel getPnlCards() {
		return pnlCards;
	}

	public void setPnlCards(JPanel pnlCards) {
		this.pnlCards = pnlCards;
	}

	public String getPanelOnScreenName() {
		return panelOnScreenName;
	}

	public void setPanelOnScreenName(String panelOnScreenName) {
		this.panelOnScreenName = panelOnScreenName;
	}

}
