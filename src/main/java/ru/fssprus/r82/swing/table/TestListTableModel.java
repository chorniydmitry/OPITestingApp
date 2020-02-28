package ru.fssprus.r82.swing.table;

public class TestListTableModel extends CommonTableModel {
	private static final long serialVersionUID = -6633865367305136653L;
	private static final int COLUMN_IS_ACTIVE_INDEX = 4;

	public TestListTableModel(int[] widths, String[] names) {
		super(widths, names);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == COLUMN_IS_ACTIVE_INDEX)
			return Boolean.class;
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
