package ru.fssprus.r82.swing.table;

import javax.swing.JComboBox;

public class SetListTableModel extends CommonTableModel {

	private static final long serialVersionUID = 1178833902180451837L;
	private static final int COLUMN_SETNAME_INDEX = 0;

	public SetListTableModel(int[] widths, String[] names) {
		super(widths, names);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == COLUMN_SETNAME_INDEX)
			return JComboBox.class;
		return super.getColumnClass(column);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Object[] row = getRowData(rowIndex);

		row[columnIndex] = aValue;

		getOnScreenDataList().set(rowIndex, row);
		fireTableRowsUpdated(rowIndex, columnIndex);
	}

}
