package ru.fssprus.r82.ui.main.mainFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import ru.fssprus.r82.ui.dialogs.DialogBuilder;
import ru.fssprus.r82.ui.utils.ImagePanel;
import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.AppConstants.Dialogs;
import ru.fssprus.r82.utils.ImageUtils;

/**
 * @author Chernyj Dmitry
 *
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -354084726011189758L;
	
	private static final String BTN_TESTING_CAPTION = Dialogs.TEST_TEXT;
	private static final String BTN_ADMIN_CAPTION = Dialogs.ADMIN_TEXT;
	private static final String BTN_STATISTICS_CAPTION = Dialogs.STATISTICS_TEXT;
	private static final String BTN_EXIT_CAPTION = "ВЫХОД";
	private static final ImageIcon BACKGROUND_IMAGE = new ImageIcon(MainFrame.class.getResource("/gerb-1600x900.jpg"));

	private JButton btnTest = new JButton(BTN_TESTING_CAPTION);
	private JButton btnAdmin = new JButton(BTN_ADMIN_CAPTION);
	private JButton btnStatistics = new JButton(BTN_STATISTICS_CAPTION);
	private JButton btnExit = new JButton(BTN_EXIT_CAPTION);
	
	private ImagePanel contentPane;

	private Dimension dimButtonSize = new Dimension(
			AppConstants.MAINFRAME_BTN_WIDTHS,
			AppConstants.MAINFRAME_BTN_HEIGHTS);

	public MainFrame() {
		btnTest.setIcon(new ImageIcon(getClass().getResource(Dialogs.TEST_ICON)));
		btnStatistics.setIcon(new ImageIcon(getClass().getResource(Dialogs.STATISTICS_ICON)));
		btnAdmin.setIcon(new ImageIcon(getClass().getResource(Dialogs.ADMIN_ICON)));
		btnExit.setIcon(new ImageIcon(getClass().getResource(Dialogs.EXIT_ICON)));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new FlowLayout());

		setButtonsSizes();
		
		configureImagePanel();
		
		setContentPane(contentPane);

		addComponents();

		addListeners();
		
		setFullScreen();
		
		setVisible(true);
	}
	
	private void configureImagePanel() {
		int width = getToolkit().getScreenSize().width;
		ImageIcon imageResized = ImageUtils.resizeImage(BACKGROUND_IMAGE, width);
		
		contentPane = new ImagePanel(imageResized);
	}
	
	private void setFullScreen() {
		dispose();      
        setUndecorated(true);
        setBounds(0,0,getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
	}

	private void addComponents() {
		contentPane.setLayout(new FlowLayout());
		contentPane.add(btnTest);
		contentPane.add(btnStatistics);
		contentPane.add(btnAdmin);
		contentPane.add(btnExit);
	}

	private void setButtonsSizes() {
		btnTest.setPreferredSize(dimButtonSize);
		btnStatistics.setPreferredSize(dimButtonSize);
		btnAdmin.setPreferredSize(dimButtonSize);
		btnExit.setPreferredSize(dimButtonSize);
	}
	
	private void initNewTestDialog() {
		DialogBuilder.showNewTestDialog();
	}
	
	private void initAdminDialog() {
		DialogBuilder.showAdminDialog();
	}
	
	private void initStatisticsDialog() {
		DialogBuilder.showStatisticsDialog();
	}

	private void addListeners() {
		btnTest.addActionListener(listener -> initNewTestDialog());
		btnAdmin.addActionListener(listener -> initAdminDialog());
		btnStatistics.addActionListener(listener -> initStatisticsDialog());

		btnExit.addActionListener(listener-> System.exit(0));
	}
}
