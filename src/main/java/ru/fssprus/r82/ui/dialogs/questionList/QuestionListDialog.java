package ru.fssprus.r82.ui.dialogs.questionList;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.fssprus.r82.ui.dialogs.DialogWithPassword;
import ru.fssprus.r82.ui.table.CommonTable;
import ru.fssprus.r82.ui.table.CommonTableModel;
import ru.fssprus.r82.ui.table.TablePanel;
import ru.fssprus.r82.utils.AppConstants;

/**
 * @author Chernyj Dmitry
 *
 */
public class QuestionListDialog extends DialogWithPassword {
	private static final String SPECNAME_FILTER = "SPECNAME";

	private static final String QUESTIONNAME_FILTER = "QUESTIONNAME";

	private static final String ID_FILTER = "ID";

	private static final long serialVersionUID = -8319908967500731744L;

	private static final String FILTER_PANEL_TITLE = "Панель фильтрации";
	
	public static final String LBL_ID_CAPTION_RU = "ID";
	public static final String LBL_QUESTION_NAME_CAPTION_RU = "Текст вопроса";
	public static final String LBL_SPECIFICATION_CAPTION_RU = "Набор";
	public static final String BTN_FILTER_CAPTION_RU = "Фильтр";
	public static final String BTN_RESET_CAPTION_RU = "Сбросить";

	private JPanel pnlFilter = new JPanel();

	private JLabel lblId = new JLabel(LBL_ID_CAPTION_RU);
	private JLabel lblQuestionName = new JLabel(LBL_QUESTION_NAME_CAPTION_RU);
	private JLabel lblSpecs = new JLabel(LBL_SPECIFICATION_CAPTION_RU);

	private JTextField tfId = new JTextField(AppConstants.QLDIALOG_TF_SIZE);
	private JTextField tfQuestionName = new JTextField(AppConstants.QLDIALOG_TF_SIZE);
	private JTextField tfSpecs = new JTextField(AppConstants.QLDIALOG_TF_SIZE);
	
	private JButton btnFilter = new JButton(BTN_FILTER_CAPTION_RU);
	private JButton btnClearFilters = new JButton(BTN_RESET_CAPTION_RU);

	private TablePanel tablePanel;

	public QuestionListDialog(int width, int height, String title, Path icon, JFrame parent) {
		super(width, height, title, icon, parent);
	
		//TODO: MOVE THESE???
		initTable();
		
		getRootPane().setDefaultButton(btnFilter);
		
	}
	
	private void initTable() {
		int[] widths = AppConstants.QLDIALOG_TABLE_COL_WIDTHS_ARR;
		String[] names = AppConstants.QLDIALOG_TABLE_COL_CAPTIONS_ARR;
		tablePanel = new TablePanel(true, true, new CommonTableModel(widths, names));
	}
	
	@Override
	protected void layoutDialog() {
		JPanel contentPanel = getContentPanel();
		
		contentPanel.setLayout(new BorderLayout());
		
		contentPanel.add(pnlFilter, BorderLayout.NORTH);
		contentPanel.add(tablePanel, BorderLayout.CENTER);
		
		setPanelFilterNames();
		layoutPanelFilter();
		
	}
	
	
	private void setPanelFilterNames() {
		tfId.setName(ID_FILTER);
		tfQuestionName.setName(QUESTIONNAME_FILTER);
		tfSpecs.setName(SPECNAME_FILTER);
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
		
		// EMPTY SPACE

		pnlFilter.setVisible(true);
	}
	

	public JButton getBtnFilter() {
		return btnFilter;
	}

	public void setBtnFilter(JButton btnFilter) {
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

	public JButton getBtnClearFilters() {
		return btnClearFilters;
	}

	public void setBtnClearFilters(JButton btnClearFilters) {
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

	public JPanel getPnlFilter() {
		return pnlFilter;
	}

	public void setPnlFilter(JPanel pnlFilter) {
		this.pnlFilter = pnlFilter;
	}
	
	
	
	
}