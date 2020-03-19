package ru.fssprus.r82.swing.table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ru.fssprus.r82.swing.utils.JGreenButton;

/**
 * @author Chernyj Dmitry
 *
 */
public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String BTN_ADD_CAPTION = "Добавить";
	private static final String BTN_REMOVE_CAPTION = "Удалить";
	public static final String BTN_EDIT_CAPTION = "Редактировать";
	public static final String BTN_SAVE_CAPTION = "Сохранить";
	private static final String BTN_CANCEL_CAPTION = "Отменить";
	
	private static final String BTN_NEXT_CAPTION = ">";
	private static final String BTN_PREVIOUS_CAPTION = "<";
	private static final String LBL_PAGE_CAPTION = "Страница: ";

	private static final String TF_PAGE_DEF_TEXT = "1";
	private static final String LBL_PAGES_TOTAL_DEF_TEXT = " из ";
	
	private static final int TF_PAGE_LENGTH = 3;
	
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEditAndSave;
	private JButton btnCancel;
	private JPanel pnlTop;
	
	private JButton btnNext;
	private JButton btnPrevious;
	private JLabel lblPage;
	private JTextField tfPage;
	private JLabel lblPagesTotal;
	private JPanel pnlBottom;
	
	private boolean isPanelTopShowing;
	private boolean isPanelBottomShowing;
	
	private CommonTable table;
	private JScrollPane scrollPane;
	
	
	public TablePanel(boolean isAddEditRemoveButtonsShowing, boolean isPagesPanelShowing, CommonTableModel tabModel) {
		isPanelTopShowing = isAddEditRemoveButtonsShowing;
		isPanelBottomShowing = isPagesPanelShowing;
		initTable(tabModel);
		initPanel();
		layoutPanel();
		
	}
	
	private void initTable(CommonTableModel tabModel) {
		table = new CommonTable(tabModel);
	}
	
	private void initPanel() {
		initComponents();
	}
	
	public void clearTable() {
		this.getTable().getTabModel().clearTable();
	}
	
	public void addRow(Object[] row) {
		this.getTable().getTabModel().addRow(row);
	}
	
	public void addData(ArrayList<Object[]> data) {
		this.getTable().getTabModel().addData(data);
	}
	
	public void setRow(Object[] row, int rowIndex) {
		this.getTable().getTabModel().setRow(row, rowIndex);
	}
	
	public Object[] getRow(int rowIndex) {
		return this.getTable().getTabModel().getRowData(rowIndex);
	}
	
	public void updateRow(Object[] row, int rowIndex) {
		this.getTable().getTabModel().updateRow(row, rowIndex);
	}
	
	public void setValueAt(Object value,int rowIndex, int colIndex) {
		this.getTable().getTabModel().setValueAt(value, rowIndex, colIndex);
	}
	
	public ArrayList<Object[]> getDataList() {
		return this.getTable().getTabModel().getOnScreenDataList();
	}
	
	public int getColumnCount() {
		return table.getColumnCount();
	}
	
	public int getRowCount() {
		return table.getRowCount();
	}
	
	public void update() {
		this.getTable().getTabModel().update();
	}
	
	public void updatePages(int current, int maximum) {
		boolean nextEnabled = (current == maximum) ? false : true;
		boolean prevEnabled = (current == 1) ? false : true;
		
		getBtnNext().setEnabled(nextEnabled);
		getBtnPrevious().setEnabled(prevEnabled);
		
		getTfPage().setText(String.valueOf(current));
		getLblPagesTotal().setText(String.valueOf(maximum));
		
	}
	
	private void initComponents() {
		if(isPanelTopShowing) {
			btnAdd = new JGreenButton(BTN_ADD_CAPTION);
			btnDelete = new JGreenButton(BTN_REMOVE_CAPTION);
			btnEditAndSave = new JGreenButton(BTN_EDIT_CAPTION);
			btnCancel = new JGreenButton(BTN_CANCEL_CAPTION);
			pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
		}
		scrollPane = new JScrollPane(table);
		
		if(isPanelBottomShowing) {
			btnNext = new JGreenButton(BTN_NEXT_CAPTION);
			lblPage = new JLabel(LBL_PAGE_CAPTION);
			tfPage = new JTextField(TF_PAGE_LENGTH);
			tfPage.setText(TF_PAGE_DEF_TEXT);
			lblPagesTotal = new JLabel(LBL_PAGES_TOTAL_DEF_TEXT);
			btnPrevious = new JGreenButton(BTN_PREVIOUS_CAPTION);
			pnlBottom = new JPanel();
		}
			
	}
	
	private void layoutPanel() {
		layoutComponents();
		
		this.setLayout(new BorderLayout());
		
		if(isPanelTopShowing)
			this.add(pnlTop, BorderLayout.NORTH);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		if(isPanelBottomShowing)
			this.add(pnlBottom, BorderLayout.SOUTH);
		
	}
	
	private void layoutComponents() {
		if(isPanelTopShowing) {
			pnlTop.add(btnAdd);
			pnlTop.add(btnDelete);
			pnlTop.add(btnEditAndSave);
			pnlTop.add(btnCancel);
		}
		
		if(isPanelBottomShowing) {
			pnlBottom.add(btnPrevious);
			pnlBottom.add(lblPage);
			pnlBottom.add(tfPage);
			pnlBottom.add(lblPagesTotal);
			pnlBottom.add(btnNext);
		}
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
	
	public JButton getBtnEditAndSave() {
		return btnEditAndSave;
	}

	public void setBtnEditAndSave(JButton btnEditAndSave) {
		this.btnEditAndSave = btnEditAndSave;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}

	public JButton getBtnPrevious() {
		return btnPrevious;
	}

	public void setBtnPrevious(JButton btnPrevious) {
		this.btnPrevious = btnPrevious;
	}

	public JTextField getTfPage() {
		return tfPage;
	}

	public void setTfPage(JTextField tfPage) {
		this.tfPage = tfPage;
	}

	public JLabel getLblPagesTotal() {
		return lblPagesTotal;
	}

	public void setLblPagesTotal(JLabel lblPagesTotal) {
		this.lblPagesTotal = lblPagesTotal;
	}

	public CommonTable getTable() {
		return table;
	}

	public void setTable(CommonTable commonTable) {
		this.table = commonTable;
	}
}
