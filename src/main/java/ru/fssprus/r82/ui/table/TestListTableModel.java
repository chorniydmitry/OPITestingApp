package ru.fssprus.r82.ui.table;

public class TestListTableModel extends CommonTableModel {
	private static final long serialVersionUID = -6633865367305136653L;
	
	public static final int COLUMN_ENTRYNUM = 0;
	public static final int COLUMN_TESTNAME = 1;
	public static final int COLUMN_TESTTIME = 2;
	public static final int COLUMN_QUESTIONS_AMOUNT = 3;
	public static final int COLUMN_IS_ACTIVE_INDEX = 4;
	
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
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Object[] row = getRowData(rowIndex);
		row[columnIndex] = aValue;
		getOnScreenDataList().set(rowIndex, row);
		fireTableRowsUpdated(rowIndex, columnIndex);
	}
}
