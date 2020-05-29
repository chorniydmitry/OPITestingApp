package ru.fssprus.r82.ui.dialogs;

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
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.ImageUtils;

public abstract class CommonDialog extends JDialog {
	private static final long serialVersionUID = -933522897356606777L;

	private static final int EMBLEM_LEFT_INSET = 20;
	private static final String BTN_CLOSE_CAPTION = "X";

	private static final Color BACKGROUND_COLOR = AppConstants.COLOR_TOPPANEL_BACKGROUND;
	private static final Color FOREGROUND_COLOR = AppConstants.COLOR_TOPPANEL_FOREGROUND;

	protected boolean accesGained = false;
	private JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pnlContent = new JPanel();
	private JLabel lblEmblem = new JLabel();
	private JLabel lblTitle = new JLabel();
	private JButton btnClose = new JButton(BTN_CLOSE_CAPTION);

	public CommonDialog(int width, int height, String title, Path icon, JFrame parent) {
		super(parent, false);
		init(width, height, title, icon);
	}
	
	public CommonDialog(int width, int height, String title, Path icon, JDialog parent) {
		super(parent, false);
		init(width, height, title, icon);
	}
	
	public void init(int width, int height, String title, Path icon) {
		System.out.println("==================>" + getParent());
		layoutPanelTop();
		layoutDialog();
		initDialog(width, height, title, icon);
	}
	
	protected void layoutPanelTop() {
		initLblTitle();
		initTopPanel();
	}
	
	private void initDialog(int width, int height, String title, Path icon) {
		setUndecorated(true);
		setSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle(title);
		setIcon(icon);
		getRootPane().setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR));

		add(pnlTop, BorderLayout.NORTH);
		add(pnlContent, BorderLayout.CENTER);
		
		setVisible(true);
	}

	public void setIcon(Path icon) {
		ImageIcon emblem = ImageUtils.getColoredImageIcon(ImageUtils.class.getResourceAsStream(icon.toString()),
				FOREGROUND_COLOR);
		lblEmblem.setIcon(emblem);
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

	private void initLblTitle() {
		lblTitle.setForeground(FOREGROUND_COLOR);
		lblTitle.setFont(AppConstants.TOP_PANELS_TEXT_FONT);
	}


	protected JPanel getContentPanel() {
		return pnlContent;
	}

	protected abstract void layoutDialog();

	protected boolean isAccessGained() {
		return accesGained;
	}

	protected JPanel getPnlTop() {
		return pnlTop;
	}

	protected JButton getBtnClose() {
		return btnClose;
	}

	public void setTitle(String title) {
		this.lblTitle.setText(title);
	}

	public String getTitle() {
		return lblTitle.getText();
	}

}
