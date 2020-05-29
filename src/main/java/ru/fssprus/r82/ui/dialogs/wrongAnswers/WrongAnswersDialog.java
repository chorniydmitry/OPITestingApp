package ru.fssprus.r82.ui.dialogs.wrongAnswers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ru.fssprus.r82.ui.dialogs.CommonDialog;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */
public class WrongAnswersDialog extends CommonDialog {
	private static final long serialVersionUID = 3594882748640500638L;
	
	private static final String BTN_CLOSE_CAPTION = "Закрыть";
	
	private JEditorPane taWrongs = new JEditorPane();
	private JLabel lblTimeLeftSec = new JLabel();
	private JButton btnClose = new JButton(BTN_CLOSE_CAPTION);
	
	private JFrame mainFrame;
	
	public WrongAnswersDialog(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
		
		//TODO: MOVE THESE???
		mainFrame = parent;
	}
	
	@Override
	protected void layoutDialog() {
		taWrongs.setContentType(AppConstants.CONTENT_TYPE_HTML);
		taWrongs.setEditable(false);
		JScrollPane scroller = new JScrollPane(taWrongs,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension(
				this.getWidth(), 
				this.getHeight() - AppConstants.WADIALOG_TAWRONGS_HEIGHT_PADDING
				- AppConstants.TOP_PANEL_HEIGHT));
		
		addComponents(scroller);
		
	}

	private void addComponents(JScrollPane scroller) {
		JPanel contentPanel = getContentPanel();
		contentPanel.add(lblTimeLeftSec, BorderLayout.NORTH);
		contentPanel.add(scroller, BorderLayout.CENTER);
		contentPanel.add(btnClose, BorderLayout.SOUTH);
	}
	
	public JEditorPane getTaWrongs() {
		return taWrongs;
	}

	public void setTaWrongs(JEditorPane taWrongs) {
		this.taWrongs = taWrongs;
	}

	public JLabel getLblTimeLeftSec() {
		return lblTimeLeftSec;
	}

	public void setLblTimeLeftSec(JLabel lblTimeLeftSec) {
		this.lblTimeLeftSec = lblTimeLeftSec;
	}

	public JButton getBtnBottomClose() {
		return btnClose;
	}

	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}
}
