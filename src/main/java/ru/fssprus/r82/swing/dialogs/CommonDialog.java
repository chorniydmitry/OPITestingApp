package ru.fssprus.r82.swing.dialogs;

/**
 * @author Chernyj Dmitry
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.ApplicationConfiguration;

public abstract class CommonDialog extends JDialog {
	private static final long serialVersionUID = -933522897356606777L;

	private static final int EMBLEM_LEFT_INSET = 20;
	private static final String BTN_CLOSE_CAPTION = "X";
	
	private static final Color BACKGROUND_COLOR = new Color(Integer.parseInt(ApplicationConfiguration.getItem("color.main"), 16));

	protected boolean accesGained = false;
	private JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pnlContent = new JPanel();
	private JLabel lblEmblem = new JLabel();
	private JLabel lblTitle = new JLabel();
	private JButton btnClose = new JButton(BTN_CLOSE_CAPTION);
	
	public CommonDialog(int width, int height, JFrame parent, boolean isModal) {
		super(parent, isModal);
		initDialog(width, height);

	}
	
	public CommonDialog(int width, int height, JFrame parent) {
		super(parent, false);
		initDialog(width, height);
	}
	
	private void initDialog(int width, int height){
		setUndecorated(true);
		setSize(new Dimension(width, height));
		getRootPane().setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR));

		add(pnlTop, BorderLayout.NORTH);
		add(pnlContent, BorderLayout.CENTER);
	}

	private void initTopPanel() {
		pnlTop.setPreferredSize(new Dimension(this.getWidth(), AppConstants.TOP_PANEL_HEIGHT));
		pnlTop.setBackground(BACKGROUND_COLOR);

		pnlTop.setLayout(new GridBagLayout());

		pnlTop.add(lblEmblem, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(0, EMBLEM_LEFT_INSET, 0, 0), 0, 0));
		pnlTop.add(lblTitle, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		pnlTop.add(btnClose, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.NORTHEAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

	}

	protected void layoutPanelTop(String text, ImageIcon emblem) {
		initLblTitle();
		initTopPanel();
		lblTitle.setText(text);
		lblEmblem.setIcon(emblem);
	}

	private void initLblTitle() {
		Color tableSelectionForeground = new Color(Integer.parseInt(ApplicationConfiguration.getItem("color.header.text"), 16));
		lblTitle.setForeground(tableSelectionForeground);
		lblTitle.setFont(AppConstants.TOP_PANELS_TEXT_FONT);
	}

	protected JPanel getContentPanel() {
		return pnlContent;
	}

	protected void loadDialog() {
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void init() {
		loadDialog();
		layoutDialog();
	}

	protected abstract void layoutDialog();

	protected abstract String getSection();

	protected abstract String getTitleText();

	protected abstract void layoutPanelTop();

	protected boolean isAccessGained() {
		return accesGained;
	}

	protected JPanel getPnlTop() {
		return pnlTop;
	}

	protected JButton getBtnClose() {
		return btnClose;
	}

}
