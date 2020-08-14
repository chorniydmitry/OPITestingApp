package ru.fssprus.r82.main;

import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;

import ru.fssprus.r82.entity.Password;
import ru.fssprus.r82.service.PasswordService;
import ru.fssprus.r82.ui.dialogs.DialogBuilder;
import ru.fssprus.r82.ui.main.mainFrame.MainFrame;
import ru.fssprus.r82.ui.utils.UIManagerConfigurator;


/**
 * @author Chernyj Dmitry
 *
 */

public class Application {

	private static void appStart() {
		dropPasswords();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				DialogBuilder.setParent(mainFrame);
			}

		});
	}
	
	public static void dropPasswords() {
//		List<Password> pass = new PasswordService().getAll();
//		pass.forEach(p->new PasswordService().update(p.getSectionName(), ""));
	}

	public static void main(String[] args) throws IOException {

//		Flyway flyway = Flyway.configure().dataSource(configuration.getProperty("connection.url"), 
//				configuration.getProperty("connection.username"), 
//				configuration.getProperty("connection.password")).load();
//		flyway.migrate();


		UIManagerConfigurator.configure();
		appStart();

	}

}
