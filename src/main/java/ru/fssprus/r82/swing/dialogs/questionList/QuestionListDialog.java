package ru.fssprus.r82.swing.dialogs.questionList;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ru.fssprus.r82.service.QuestionSetService;
import ru.fssprus.r82.swing.dialogs.DialogWithPassword;
import ru.fssprus.r82.swing.table.CommonTable;
import ru.fssprus.r82.swing.table.CommonTableModel;
import ru.fssprus.r82.swing.table.TablePanel;
import ru.fssprus.r82.swing.utils.JGreenButton;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionListDialog extends DialogWithPassword {
	private static final long serialVersionUID = -8319908967500731744L;
	private static final String SECTION = AppConstants.QUESTION_EDIT_DIALOG;
	private static final String TITLE = AppConstants.QUESTION_EDIT_TEXT;
	private static final String ICON = AppConstants.QUESTION_EDIT_ICON;
	
	private static final String FILTER_PANEL_TITLE = "Панель фильтрации";
	
	public static final String LBL_ID_CAPTION_RU = "ID";
	public static final String LBL_QUESTION_NAME_CAPTION_RU = "Текст вопроса";
	public static final String LBL_SPECIFICATION_CAPTION_RU = "Специализация";
	public static final String LBL_LEVELS_CAPTION_RU = "Сложность";
	public static final String BTN_FILTER_CAPTION_RU = "Фильтр";
	public static final String BTN_RESET_CAPTION_RU = "Сбросить";

	private JPanel pnlFilter = new JPanel();

	private JLabel lblId = new JLabel(LBL_ID_CAPTION_RU);
	private JLabel lblQuestionName = new JLabel(LBL_QUESTION_NAME_CAPTION_RU);
	private JLabel lblSpecs = new JLabel(LBL_SPECIFICATION_CAPTION_RU);
	private JLabel lblLevels = new JLabel(LBL_LEVELS_CAPTION_RU);

	private JTextField tfId = new JTextField(AppConstants.QLDIALOG_TF_SIZE);
	private JTextField tfQuestionName = new JTextField(AppConstants.QLDIALOG_TF_SIZE);
	private JTextField tfSpecs = new JTextField(AppConstants.QLDIALOG_TF_SIZE);
	private JTextField tfLevels = new JTextField(AppConstants.QLDIALOG_TF_SIZE);
	
	private JGreenButton btnFilter = new JGreenButton(BTN_FILTER_CAPTION_RU);
	private JGreenButton btnClearFilters = new JGreenButton(BTN_RESET_CAPTION_RU);

	private TablePanel tablePanel;

	public QuestionListDialog(int width, int height, JFrame parent) {
		super(width, height, parent);
		initTable();
		
		getRootPane().setDefaultButton(btnFilter);
	}
	
	private void initTable() {
		int[] widths = AppConstants.QLDIALOG_TABLE_COL_WIDTHS_ARR;
		String[] names = AppConstants.QLDIALOG_TABLE_COL_CAPTIONS_ARR;
		tablePanel = new TablePanel(true, true, new CommonTableModel(widths, names));
	}
	
	@Override
	public void layoutPanelTop() {
		ImageIcon emblem = new ImageIcon(getClass().getResource(ICON));
		super.layoutPanelTop(TITLE, emblem);
	}
	
	@Override
	protected void layoutDialog() {
		JPanel contentPanel = getContentPanel();
		
		contentPanel.setLayout(new BorderLayout());
		
		contentPanel.add(pnlFilter, BorderLayout.NORTH);
		contentPanel.add(tablePanel, BorderLayout.CENTER);
		
		layoutPanelFilter();
		
	}

	@Override
	public void init() {
		initTable();
		super.init();
	}
	
	
	@Override
	protected String getSection() {
		return SECTION;
	}
	
	@Override
	protected String getTitleText() {
		return TITLE;
	}


	private void layoutPanelFilter() {
		pnlFilter.setLayout(new GridBagLayout());

		pnlFilter.setBorder(BorderFactory.createTitledBorder(FILTER_PANEL_TITLE));

		// gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill,
		// insets(top, left, botom, right), ipadx, ipady

		// 1st row
		pnlFilter.add(lblId, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.add(tfId, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.add(lblQuestionName, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.add(tfQuestionName, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.add(btnFilter, new GridBagConstraints(4, 0, 1, GridBagConstraints.REMAINDER, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		pnlFilter.add(btnClearFilters, new GridBagConstraints(5, 0, 1, GridBagConstraints.REMAINDER, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// 2nd row
		pnlFilter.add(lblSpecs, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.add(tfSpecs, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.add(lblLevels, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.add(tfLevels, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		pnlFilter.setVisible(true);
	}
	

	public JButton getBtnFilter() {
		return btnFilter;
	}

	public void setBtnFilter(JGreenButton btnFilter) {
		this.btnFilter = btnFilter;
	}

	public JTextField getTfId() {
		return tfId;
	}

	public void setTfId(JTextField tfId) {
		this.tfId = tfId;
	}

	public JTextField getTfQuestionName() {
		return tfQuestionName;
	}

	public void setTfQuestionName(JTextField tfQuestionName) {
		this.tfQuestionName = tfQuestionName;
	}

	public JTextField getTfSpecs() {
		return tfSpecs;
	}

	public void setTfSpecs(JTextField tfSpecs) {
		this.tfSpecs = tfSpecs;
	}

	public JTextField getTfLevels() {
		return tfLevels;
	}

	public void setTfLevels(JTextField tfLevels) {
		this.tfLevels = tfLevels;
	}

	public JButton getBtnClearFilters() {
		return btnClearFilters;
	}

	public void setBtnClearFilters(JGreenButton btnClearFilters) {
		this.btnClearFilters = btnClearFilters;
	}

	public TablePanel getTabPanel() {
		return tablePanel;
	}

	public void setTabPanel(TablePanel tabPanel) {
		this.tablePanel = tabPanel;
	}
	
	public CommonTable getTable() {
		return tablePanel.getTable();
	}
	
	public CommonTableModel getTableModel() {
		return getTable().getTabModel();
	}
	
	
}
