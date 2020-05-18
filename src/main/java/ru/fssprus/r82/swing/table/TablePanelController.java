package ru.fssprus.r82.swing.table;


import javax.swing.ListSelectionModel;

import ru.fssprus.r82.utils.Utils;

/**
 * @author Chernyj Dmitry
 *
 */
public class TablePanelController {

	private TablePanel tablePanel;
	private CommonTable table;
	private UpdatableController subscriberController;
	private boolean isEditing = false;
	private boolean isReadyToSave = false;

	public TablePanelController(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
		this.table = tablePanel.getTable();

		setListeners();

		init();
	}

	private void init() {
		tablePanel.getBtnDelete().setEnabled(false);
		tablePanel.getBtnEditAndSave().setEnabled(false);
		tablePanel.getTable().getTabModel().setEditing(false);
		tablePanel.getBtnCancel().setVisible(false);
	}

	private void setListeners() {
		if(tablePanel.isPanelTopShowing()) {
			tablePanel.getBtnAdd().addActionListener(listener -> doAddAction());
			tablePanel.getBtnDelete().addActionListener(listener -> doDeleteAction());
			tablePanel.getBtnEditAndSave().addActionListener(listener -> doEditOrSaveAction());
			tablePanel.getBtnCancel().addActionListener(listener -> doCancelAction());
		}
		
		if (tablePanel.isPanelBottomShowing()) {
			tablePanel.getBtnNext().addActionListener(listener -> doNextAction());
			tablePanel.getBtnPrevious().addActionListener(listener -> doPreviousAction());
			tablePanel.getTfPage().addActionListener(listener -> doChangePageAction());
		}

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();

		cellSelectionModel.addListSelectionListener(listener -> doTableRowChangedAction());
	}

	private void doTableRowChangedAction() {
		if (table.getSelectedRows().length > 0) {
			int[] selectedRow = table.getSelectedRows();

			table.setLastSelectedIndex(selectedRow[0]);
			subscriberController.selectionChanged(selectedRow[0]);
		}

		updateButtons();

	}

	private void updateButtons() {
		tablePanel.getBtnAdd().setEnabled(!isEditing);

		updateBtnDelete();

		updateButtonEditAndSave();

		boolean isBtnCancelVisible = isEditing ? true : false;
		tablePanel.getBtnCancel().setVisible(isBtnCancelVisible);

		table.setEnabled(!isEditing);
		tablePanel.getTable().getTabModel().setEditing(isEditing);
	}

	private void updateBtnDelete() {
		if (table.getSelectedRow() == CommonTable.NO_ROWS_SELECTED)
			tablePanel.getBtnDelete().setEnabled(false);
		else {
			tablePanel.getBtnDelete().setEnabled(!isEditing);
		}
	}

	private void updateButtonEditAndSave() {
		String textForBtnEditAndSave = isEditing ? TablePanel.BTN_SAVE_CAPTION : TablePanel.BTN_EDIT_CAPTION;
		tablePanel.getBtnEditAndSave().setText(textForBtnEditAndSave);
		tablePanel.getBtnEditAndSave().setEnabled(!isEditing);
		if (isEditing && !isReadyToSave || table.getSelectedRow() == CommonTable.NO_ROWS_SELECTED) {
			tablePanel.getBtnEditAndSave().setEnabled(false);
		}
	}

	private void doAddAction() {
		table.unselectAll();
		table.getTabModel().uncolorAll();

		tablePanel.addRow(new Object[table.getTabModel().getColumnCount()]);
		tablePanel.getTable().scrollTableDown();
		this.subscriberController.addEntry(table.getRowCount());

		isEditing = true;
		updateButtons();
	}

	private void doDeleteAction() {
		int lastSelectedIndex = table.getLastSelectedIndex();

		if (lastSelectedIndex >= 0 && lastSelectedIndex <= table.getRowCount())
			subscriberController.delete(lastSelectedIndex);

		table.unselectAll();
		isEditing = false;
	}

	private void doEditOrSaveAction() {
		// BTN_SAVE_CAPTION
		if (tablePanel.getBtnEditAndSave().getText() == TablePanel.BTN_EDIT_CAPTION) {
			isEditing = true;
			updateButtons();
			subscriberController.edit();
			return;

		}
		if (tablePanel.getBtnEditAndSave().getText() == TablePanel.BTN_SAVE_CAPTION) {
			isEditing = false;
			updateButtons();
			subscriberController.save();
		}
	}

	private void doCancelAction() {
		tablePanel.getTable().clearSelection();
		isEditing = false;
		updateButtons();
		subscriberController.cancel();
	}

	private void doChangePageAction() {
		table.unselectAll();
		if (Utils.isNumeric(tablePanel.getTfPage().getText()))
			subscriberController.goToPage(Integer.parseInt(tablePanel.getTfPage().getText()));
		else {
			tablePanel.getTfPage().setText("");
		}

	}

	private void doPreviousAction() {
		table.unselectAll();
		subscriberController.previousPage();
	}

	private void doNextAction() {
		table.unselectAll();
		subscriberController.nextPage();
	}

	public void setSubscriber(UpdatableController updatableController) {
		this.subscriberController = updatableController;
	}

	public UpdatableController getSubscriber() {
		return subscriberController;
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
		updateButtons();
	}

	public boolean isReadyToSave() {
		return isReadyToSave;
	}

	public void setReadyToSave(boolean isReadyToSave) {
		this.isReadyToSave = isReadyToSave;
	}

}
