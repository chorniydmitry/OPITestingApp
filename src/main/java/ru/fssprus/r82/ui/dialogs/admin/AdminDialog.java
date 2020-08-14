package ru.fssprus.r82.ui.dialogs.admin;

/**
 * @author Chernyj Dmitry
 *
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ru.fssprus.r82.ui.dialogs.DialogWithPassword;

public class AdminDialog extends DialogWithPassword {

	private static final long serialVersionUID = -2557630253920656451L;

	private static final String BTN_PASSWORDS_CAPTION = "Установка паролей";
	private static final String BTN_QEDIT_CAPTION = "Редактор вопросов";
	private static final String BTN_TCOSTRUCTOR = "Редактор тестов";
	private static final String BTN_QLOAD_CAPTION = "Редактор наборов вопросов";
	
	private JButton btnQuestionLoad = new JButton(BTN_QLOAD_CAPTION);
	private JButton btnQuestionEdit = new JButton(BTN_QEDIT_CAPTION);
	private JButton btnTestConstructor = new JButton(BTN_TCOSTRUCTOR);
	private JButton btnPasswords = new JButton(BTN_PASSWORDS_CAPTION);
	
	public AdminDialog(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
	}

	@Override
	protected void layoutDialog() {
		System.out.println("ADMIN LAYOUT");
		JPanel contentPanel = getContentPanel();
		contentPanel.setLayout(new GridBagLayout());
		
		contentPanel.add(btnQuestionLoad, new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
		
		contentPanel.add(btnQuestionEdit, new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
		
		contentPanel.add(btnTestConstructor, new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
		
		contentPanel.add(btnPasswords, new GridBagConstraints(0, 3, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 10, 10));
		
	}
	
	
	public JButton getBtnQuestionLoad() {
		return btnQuestionLoad;
	}

	public void setBtnQuestionLoad(JButton btnQuestionLoad) {
		this.btnQuestionLoad = btnQuestionLoad;
	}

	public JButton getBtnQuestionEdit() {
		return btnQuestionEdit;
	}

	public void setBtnQuestionEdit(JButton btnQuestionEdit) {
		this.btnQuestionEdit = btnQuestionEdit;
	}

	public JButton getBtnPasswords() {
		return btnPasswords;
	}

	public void setBtnPasswords(JButton btnPasswords) {
		this.btnPasswords = btnPasswords;
	}

	public JButton getBtnTestConstructor() {
		return btnTestConstructor;
	}

	public void setBtnTestConstructor(JButton btnTestConstructor) {
		this.btnTestConstructor = btnTestConstructor;
	}

}
