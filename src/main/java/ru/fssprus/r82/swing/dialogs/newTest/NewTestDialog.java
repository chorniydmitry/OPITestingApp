package ru.fssprus.r82.swing.dialogs.newTest;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.fssprus.r82.swing.dialogs.DialogWithPassword;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */
public class NewTestDialog extends DialogWithPassword {
	private static final long serialVersionUID = 1342455118946206792L;
	private static final String SECTION = AppConstants.NEWTEST_DIALOG;
	private static final String TITLE = AppConstants.NEWTEST_TEXT;
	private static final String ICON = AppConstants.NEWTEST_ICON;
	
	private static final String LBL_INFO_TEXT = "ИНФОРМАЦИЯ";
	private static final String LBL_NAME_TEXT = "Ваше имя:";
	private static final String LBL_SURNAME_TEXT = "Ваша фамилия:";
	private static final String LBL_SECONDNAME_TEXT = "Ваше отчество:";
	private static final String LBL_SPECIFICATION_TEXT = "Название теста:";
	private static final String BTN_BEGIN_CAPTION = "Начать";
	private static final String BTN_CANCEL_CAPTION = "Отмена";
	
	private JLabel lblInfo = new JLabel(LBL_INFO_TEXT);
	private JLabel lblName = new JLabel(LBL_NAME_TEXT);
	private JLabel lblSurname = new JLabel(LBL_SURNAME_TEXT);
	private JLabel lblSecondName = new JLabel(LBL_SECONDNAME_TEXT);
	private JLabel lblSpecification = new JLabel(LBL_SPECIFICATION_TEXT);
	
	private JTextField tfName = new JTextField();
	private JTextField tfSurname = new JTextField();
	private JTextField tfSecondName = new JTextField();
	private JComboBox<String> cbSpecification = new JComboBox<String>();
	
	private JButton btnBegin = new JButton(BTN_BEGIN_CAPTION);
	private JButton btnCancel = new JButton(BTN_CANCEL_CAPTION);
	
	public NewTestDialog(int width, int height, JFrame parent) {
		super(width, height, parent);
	}
	
	@Override
	public void layoutPanelTop() {
		ImageIcon emblem = new ImageIcon(getClass().getResource(ICON));
		super.layoutPanelTop(TITLE, emblem);
	}
	
	@Override
	protected void layoutDialog() {
		JPanel contentPanel = getContentPanel();
		contentPanel.setLayout(new GridBagLayout());;
		//gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady
		
		// 1st row
		contentPanel.add(lblInfo, new GridBagConstraints(0, 0, 4, 1, 4, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		
		// 2nd row
		contentPanel.add(lblName, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		contentPanel.add(tfName, new GridBagConstraints(1, 2, 4, 1, 4, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

		// 3rd row
		contentPanel.add(lblSurname, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		contentPanel.add(tfSurname, new GridBagConstraints(1, 1, 4, 1, 4, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		
		// 4th row
		contentPanel.add(lblSecondName, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		contentPanel.add(tfSecondName, new GridBagConstraints(1, 3, 4, 1, 4, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		
		// 5th row
		contentPanel.add(lblSpecification, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		
		contentPanel.add(cbSpecification, new GridBagConstraints(1, 4, 4, 1, 4, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		
		// 6th row
		contentPanel.add(btnCancel, new GridBagConstraints(0, 5, 2, 1, 2, 1, GridBagConstraints.NORTHEAST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		
		contentPanel.add(btnBegin, new GridBagConstraints(2, 5, 2, 1, 2, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
	}
	
	@Override
	protected String getSection() {
		return SECTION;
	}
	
	@Override
	protected String getTitleText() {
		return TITLE;
	}
	
		
	public void resetUserInputComponents() {
		tfName.setBackground(Color.WHITE);
		tfSurname.setBackground(Color.WHITE);
		tfSecondName.setBackground(Color.WHITE);
	}
	
	public JTextField getTfName() {
		return tfName;
	}


	public void setTfName(JTextField tfName) {
		this.tfName = tfName;
	}


	public JTextField getTfSurname() {
		return tfSurname;
	}


	public void setTfSurname(JTextField tfSurname) {
		this.tfSurname = tfSurname;
	}


	public JTextField getTfSecondName() {
		return tfSecondName;
	}


	public void setTfSecondName(JTextField tfSecondName) {
		this.tfSecondName = tfSecondName;
	}


	public JComboBox<String> getCbSpecification() {
		return cbSpecification;
	}

	public void setCbSpecification(JComboBox<String> cbSpecification) {
		this.cbSpecification = cbSpecification;
	}

	public JButton getBtnBegin() {
		return btnBegin;
	}


	public void setBtnBegin(JButton btnBegin) {
		this.btnBegin = btnBegin;
	}


	public JButton getBtnCancel() {
		return btnCancel;
	}


	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
	
}

