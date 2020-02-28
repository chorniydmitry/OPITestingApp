package ru.fssprus.r82.swing.dialogs.testCreator;
/**
 * @author Chernyj Dmitry
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import g.cope.swing.autocomplete.jcombobox.AutocompleteJComboBox;
import ru.fssprus.r82.swing.dialogs.DialogWithPassword;
import ru.fssprus.r82.swing.table.CommonTableModel;
import ru.fssprus.r82.swing.table.TablePanel;
import ru.fssprus.r82.swing.table.TestListTableModel;
import ru.fssprus.r82.swing.utils.JGreenButton;
import ru.fssprus.r82.utils.AppConstants;

public class TestConstructorDiaolg extends DialogWithPassword {
	private static final long serialVersionUID = -691574072269035086L;
	private static final String SECTION = AppConstants.TESTCONSTRUCTOR_DIALOG;
	private static final String TITLE = AppConstants.TESTCONSTRUCTOR_TEXT;
	private static final String ICON = AppConstants.TESTCONSTRUCTOR_ICON;

	private static final String LBL_TOTAL_QUEST_AMOUNT_CAPTION = "Всего вопросов:";
	private static final String LBL_QUESTIONSET_AMOUNT_CAPTION = "Вопросов из набора:";
	private static final String LBL_QUESTIONSET_CAPTION = "Набор вопросов:";
	private static final String LBL_TEST_TIME_CAPTION = "Время на тест(сек):";
	private static final String LBL_TEST_IS_ACTIVE_CAPTION = "Тест активен?";
	private static final String BTN_CHECKANDSAVE_CAPTION = "Проверить и сохранить";
	private static final String BTN_ADDSET_CAPTION = "Добавить";

	private static final int TABLE_LIST_HEIGHT = 300;
	private static final int TABLE_CURRENT_HEIGHT = 300;

	private static final int TF_WIDTH = 25;

	private TablePanel tblList;
	private TablePanel tblCurrent;

	private JPanel pnlEdit = new JPanel();
	private JLabel lblQuestAmount = new JLabel(LBL_TOTAL_QUEST_AMOUNT_CAPTION);
	private JLabel lblTestTime = new JLabel(LBL_TEST_TIME_CAPTION);
	private JLabel lblTestIsActive = new JLabel(LBL_TEST_IS_ACTIVE_CAPTION);

	private JLabel lblQuestionSet = new JLabel(LBL_QUESTIONSET_CAPTION);
	private JLabel lblSetQuestAmount = new JLabel(LBL_QUESTIONSET_AMOUNT_CAPTION);

	private JTextField tfQuestAmount = new JTextField(TF_WIDTH);
	private JTextField tfTestTime = new JTextField(TF_WIDTH);

	private JCheckBox cbTestIsActive = new JCheckBox();

	private AutocompleteJComboBox accbSpecName = new AutocompleteJComboBox(null);

	private JTextField tfSetQuestAmount = new JTextField(TF_WIDTH);
	
	private JGreenButton btnAddSet = new JGreenButton(BTN_ADDSET_CAPTION);

	private JGreenButton btnCheckAndSave = new JGreenButton(BTN_CHECKANDSAVE_CAPTION);

	public TestConstructorDiaolg(int width, int height, JFrame parent) {
		super(width, height, parent);
		initTablePanels();
	}

	@Override
	protected void layoutDialog() {
		layoutPanelEdit();
		getContentPanel().add(tblList, BorderLayout.NORTH);
		getContentPanel().add(tblCurrent, BorderLayout.WEST);
		getContentPanel().add(pnlEdit, BorderLayout.EAST);
	}

	private void layoutPanelEdit() {
		pnlEdit.setBorder(BorderFactory.createTitledBorder("РЕДАКТИРОВАНИЕ ТЕСТА"));
		pnlEdit.setLayout(new GridBagLayout());

		pnlEdit.add(lblQuestAmount, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		pnlEdit.add(tfQuestAmount, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));

		pnlEdit.add(lblTestTime, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		pnlEdit.add(tfTestTime, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));

		pnlEdit.add(lblTestIsActive, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		pnlEdit.add(cbTestIsActive, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));

		// -----------------------------------------------------------------------------------------------------

		pnlEdit.add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 3, 2, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		
		// -----------------------------------------------------------------------------------------------------
		
		pnlEdit.add(lblQuestionSet, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		pnlEdit.add(accbSpecName, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		
		pnlEdit.add(lblSetQuestAmount, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		pnlEdit.add(tfSetQuestAmount, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		
		pnlEdit.add(btnAddSet, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		
		// -----------------------------------------------------------------------------------------------------

		pnlEdit.add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 7, 2, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		
		// -----------------------------------------------------------------------------------------------------
		
		pnlEdit.add(btnCheckAndSave, new GridBagConstraints(0, 8, 2, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
	}

	private void initTablePanels() {
		tblList = new TablePanel(true, true, new TestListTableModel(AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_WIDTHS_ARR, AppConstants.TESTCONSTRUCTOR_TABLELIST_COL_CAPTIONS_ARR));

		tblList.setPreferredSize(new Dimension(getWidth(), TABLE_LIST_HEIGHT));

		tblCurrent = new TablePanel(true, false, new CommonTableModel(AppConstants.TESTCONSTRUCTOR_TABLECURRENT_COL_WIDTHS_ARR, AppConstants.TESTCONSTRUCTOR_TABLECURRENT_COL_CAPTIONS_ARR));
// TODO: REMOVE MAGIC NUMBERS
		tblCurrent.setPreferredSize(new Dimension(getWidth() / 2 - 25, TABLE_CURRENT_HEIGHT));
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
