package ru.fssprus.r82.swing.dialogs.testCreator;
/**
 * @author Chernyj Dmitry
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.fssprus.r82.swing.dialogs.DialogWithPassword;
import ru.fssprus.r82.swing.table.TablePanel;
import ru.fssprus.r82.swing.utils.JGreenButton;
import ru.fssprus.r82.utils.AppConstants;

public class TestConstructorDiaolg extends DialogWithPassword {
	private static final long serialVersionUID = -691574072269035086L;
	private static final String SECTION = AppConstants.TESTCONSTRUCTOR_DIALOG;
	private static final String TITLE = AppConstants.TESTCONSTRUCTOR_TEXT;
	private static final String ICON = AppConstants.TESTCONSTRUCTOR_ICON;
	
	private static final String LBL_QUEST_AMOUNT_CAPTION = "Количество вопросов:";
	private static final String BTN_CHECKANDSAVE_CAPTION = "Проверить и сохранить";
	
	private static final int TABLE_LIST_HEIGHT = 300;
	private static final int TABLE_CURRENT_HEIGHT = 300;
	
	private TablePanel tblList;
	private TablePanel tblCurrent;
	private JPanel pnlEdit = new JPanel();
	private JLabel lblQuestAmount = new JLabel(LBL_QUEST_AMOUNT_CAPTION);
	private JTextField tfQuestAmount = new JTextField();
	private JGreenButton btnCheckAndSave = new JGreenButton(BTN_CHECKANDSAVE_CAPTION);
	

	public TestConstructorDiaolg(int width, int height, JFrame parent) {
		super(width, height, parent);
		initTablePanels();
	}

	@Override
	protected void layoutDialog() {
		layoutPanelEdit();
		getContentPanel().add(tblList, BorderLayout.NORTH);
		getContentPanel().add(tblCurrent, BorderLayout.CENTER);
		getContentPanel().add(pnlEdit, BorderLayout.SOUTH);
		
	}
	
	private void layoutPanelEdit() {
		pnlEdit.add(lblQuestAmount);
		pnlEdit.add(tfQuestAmount);
		pnlEdit.add(btnCheckAndSave);
	}
	
	private void initTablePanels() {
		tblList = new TablePanel(AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_WIDTHS_ARR, 
				AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_CAPTIONS_ARR);
		
		tblList.setPreferredSize(new Dimension(getWidth(), TABLE_LIST_HEIGHT));
		
		tblCurrent = new TablePanel(AppConstants.TESTCONSTRUCTOR_TABLECURRENT_COL_WIDTHS_ARR, 
				AppConstants.TESTCONSTRUCTOR_TABLECURRENT_COL_CAPTIONS_ARR);
		
		tblCurrent.setPreferredSize(new Dimension(getWidth(), TABLE_CURRENT_HEIGHT));
	}

	@Override
	protected String getSection() {
		return SECTION;
	}

	@Override
	protected String getTitleText() {
		return TITLE;
	}

	@Override
	protected void layoutPanelTop() {
		ImageIcon emblem = new ImageIcon(getClass().getResource(ICON));
		super.layoutPanelTop(TITLE, emblem);
	}

	public TablePanel getTblList() {
		return tblList;
	}

	public void setTblList(TablePanel tblList) {
		this.tblList = tblList;
	}

	public TablePanel getTblCurrent() {
		return tblCurrent;
	}

	public void setTblCurrent(TablePanel tblCurrent) {
		this.tblCurrent = tblCurrent;
	}
	
	

}
