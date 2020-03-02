package ru.fssprus.r82.swing.dialogs.testCreator;
/**
 * @author Chernyj Dmitry
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import ru.fssprus.r82.swing.dialogs.DialogWithPassword;
import ru.fssprus.r82.swing.table.SetListTableModel;
import ru.fssprus.r82.swing.table.TablePanel;
import ru.fssprus.r82.swing.table.TestListTableModel;
import ru.fssprus.r82.swing.utils.JGreenButton;
import ru.fssprus.r82.utils.AppConstants;

public class TestConstructorDiaolg extends DialogWithPassword {
	private static final long serialVersionUID = -691574072269035086L;
	private static final String SECTION = AppConstants.TESTCONSTRUCTOR_DIALOG;
	private static final String TITLE = AppConstants.TESTCONSTRUCTOR_TEXT;
	private static final String ICON = AppConstants.TESTCONSTRUCTOR_ICON;

	private static final String BTN_SAVE_CAPTION = "Проверить и сохранить";
	
	private static final int TABLE_LIST_HEIGHT = 400;
	private static final int TABLE_CURRENT_HEIGHT = 200;
	private static final int BTN_SAVE_HEIGHT = 25;

	private TablePanel tblList;
	private TablePanel tblCurrent;
	
	private JGreenButton btnSave = new JGreenButton(BTN_SAVE_CAPTION);

	private JComboBox<String> cbAvailibleSets = new JComboBox<>();

	public TestConstructorDiaolg(int width, int height, JFrame parent) {
		super(width, height, parent);
		initComponents();
	}

	@Override
	protected void layoutDialog() {
		getContentPanel().add(tblList, BorderLayout.NORTH);
		getContentPanel().add(tblCurrent, BorderLayout.CENTER);
		getContentPanel().add(btnSave, BorderLayout.SOUTH);
	}

	private void initComponents() {
		tblList = new TablePanel(true, true, new TestListTableModel(AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_WIDTHS_ARR, AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_CAPTIONS_ARR));

		tblList.setPreferredSize(new Dimension(getWidth(), TABLE_LIST_HEIGHT));

		tblCurrent = new TablePanel(true, false, new SetListTableModel(AppConstants.TESTCONSTRUCTOR_TABLECURRENT_COL_WIDTHS_ARR, AppConstants.TESTCONSTRUCTOR_TABLECURRENT_COL_CAPTIONS_ARR));
		tblCurrent.getTable().getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbAvailibleSets));
		
		tblCurrent.setPreferredSize(new Dimension(getWidth(), TABLE_CURRENT_HEIGHT));
		
		btnSave.setPreferredSize(new Dimension(getWidth(), BTN_SAVE_HEIGHT));
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

	public JComboBox<String> getCbAvailibleSets() {
		return cbAvailibleSets;
	}

	public void setCbAvailibleSets(JComboBox<String> cbAvailibleSets) {
		this.cbAvailibleSets = cbAvailibleSets;
	}
}
