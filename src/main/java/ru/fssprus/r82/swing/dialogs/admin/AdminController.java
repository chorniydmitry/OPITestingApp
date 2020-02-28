package ru.fssprus.r82.swing.dialogs.admin;

/**
 * @author Chernyj Dmitry
 *
 */
import ru.fssprus.r82.swing.dialogs.CommonController;
import ru.fssprus.r82.swing.dialogs.DialogBuilder;

public class AdminController extends CommonController<AdminDialog> {

	public AdminController(AdminDialog dialog) {
		super(dialog);
	}

	@Override
	protected void setListeners() {
		dialog.getBtnPasswords().addActionListener(listener -> doOpenManagePasswordsDialog());
		dialog.getBtnQuestionEdit().addActionListener(listener -> doOpenQuestionEditDialog());
		dialog.getBtnQuestionLoad().addActionListener(listener -> doOpenQuestionLoagingDialog());
		dialog.getBtnTestConstructor().addActionListener(listener -> doOpenTestConstructorDialog());
	}

	private void doOpenTestConstructorDialog() {
		dialog.dispose();
		DialogBuilder.showTestConstructorDialog();
	}

	private void doOpenQuestionLoagingDialog() {
		dialog.dispose();
		DialogBuilder.showQuestionLoadingSetDialog();
		
	}

	private void doOpenQuestionEditDialog() {
		dialog.dispose();
		DialogBuilder.showQuestionListDialog();
		
	}

	private void doOpenManagePasswordsDialog() {
		dialog.dispose();
		DialogBuilder.showPasswordManageDialog();
		
	}
}
