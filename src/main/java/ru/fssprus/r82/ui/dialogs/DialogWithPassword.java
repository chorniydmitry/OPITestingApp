package ru.fssprus.r82.ui.dialogs;

import java.nio.file.Path;

/**
 * @author Chernyj Dmitry
 *
 */
import javax.swing.JFrame;

import ru.fssprus.r82.service.PasswordService;
import ru.fssprus.r82.ui.utils.MessageBox;
import ru.fssprus.r82.utils.CryptWithMD5;

public abstract class DialogWithPassword extends CommonDialog {
	private static final long serialVersionUID = -3351598510558332393L;

	public DialogWithPassword(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
		if (checkIfPasswordIsSet(getClass().getSimpleName())) {
			
			if (checkaccess(getClass().getSimpleName())) {
				accesGained = true;
			}
		} else {
			accesGained = true;
		}
	}

	private boolean checkaccess(String section) {
		PasswordService passService = new PasswordService();

		String inputedPass = MessageBox.showInputPasswordDialog(null);

		boolean accessAllowed = passService.checkPassword(getClass().getSimpleName(), CryptWithMD5.cryptWithMD5(inputedPass));

		return accessAllowed;

	}

	private boolean checkIfPasswordIsSet(String section) {
		PasswordService service = new PasswordService();
		if (service.passwordIsSet(section) > 0)
			return true;
		return false;
	}

}
