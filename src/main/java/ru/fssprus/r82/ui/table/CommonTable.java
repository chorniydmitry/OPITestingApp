package ru.fssprus.r82.ui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DropMode;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import ru.fssprus.r82.utils.AppConstants;
import ru.fssprus.r82.utils.ApplicationConfiguration;

/**
 * @author Chernyj Dmitry
 *
 */
public class CommonTable extends JTable {
	private static final long serialVersionUID = 1281533315206385819L;
	public static final int NO_ROWS_SELECTED = -1;
	
	private static final int HEX = 16;

	private CommonTableModel tabModel;

	private int lastSelectedIndex = AppConstants.NO_INDEX_SELECTED;

	private boolean[] columnMultiline;
	
	private Color foregroundSelected = new Color(Integer.parseInt(ApplicationConfiguration.getItem("color.Table.selectionForeground"), HEX));
	private Color backgroundSelected = new Color(Integer.parseInt(ApplicationConfiguration.getItem("color.Table.selectionBackground"), HEX));
	private Color foreground = new Color(Integer.parseInt(ApplicationConfiguration.getItem("color.Table.Foreground"), HEX));
	private Color background = new Color(Integer.parseInt(ApplicationConfiguration.getItem("color.Table.Background"), HEX));
	

	public CommonTable(CommonTableModel tabModel) {
		initTableModel(tabModel);

		initTable();

		updateColumnWidths(tabModel.getColumnWidths());
	}

	private void initTableModel(CommonTableModel tabModel) {
		this.tabModel = tabModel;
	}

	private void initColumns() {
		columnMultiline = new boolean[tabModel.getColumnCount()];
		for (int i = 0; i < columnMultiline.length; i++) {
			columnMultiline[i] = false;
		}
	}

	public void setColumnMultiline(int columnIndex, boolean isColumnMultiline) {
		columnMultiline[columnIndex] = isColumnMultiline;
	}

	private void initTable() {
		initColumns();

		setModel(tabModel);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setAutoscrolls(true);
		setDragEnabled(true);
		setDropMode(DropMode.INSERT_ROWS);
		setDefaultRenderer(Object.class, new MultilineCellRenderer());

	}
	
	public void select(int index) {
		this.lastSelectedIndex = index;
	}

	public void unselectAll() {
		setLastSelectedIndex(AppConstants.NO_INDEX_SELECTED);
	}

	public void scrollTableDown() {
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				int lastIndex = getRowCount() - 1;
				changeSelection(lastIndex, 0, false, false);
			}
		});
	}

	public void updateColumnWidths(int[] widths) {
		for (int i = 0; i < widths.length; i++)
			getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
	}

	public CommonTableModel getTabModel() {
		return tabModel;
	}

	public void setTabModel(CommonTableModel tabModel) {
		this.tabModel = tabModel;
	}

	public int getLastSelectedIndex() {
		return lastSelectedIndex;
	}

	public void setLastSelectedIndex(int lastSelectedIndex) {
		this.lastSelectedIndex = lastSelectedIndex;
	}
	

	public class MultilineCellRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 7406284718249927491L;
		
        public MultilineCellRenderer() {
            
            super();
            setLineWrap(true);
            setWrapStyleWord(true);
        }

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			CommonTableModel model = (CommonTableModel) table.getModel();
			
			if(isSelected) {
				setBackground(backgroundSelected);
				setForeground(foregroundSelected);
			}
			else {
				setBackground(model.getRowColor(row));
				setForeground(foreground);
			}
			
			String data = value == null ? "" : (String) value.toString();

			if (columnMultiline[column]) {
				
				int lineWidth = this.getFontMetrics(this.getFont()).stringWidth(data);
				int lineHeight = this.getFontMetrics(this.getFont()).getHeight();
				int rowWidth = table.getCellRect(row, column, true).width;

				int newRowHeight = (int) ((lineWidth / rowWidth) * (lineHeight)) + lineHeight * 2;
				if (table.getRowHeight(row) != newRowHeight) {
					table.setRowHeight(row, newRowHeight);
				}
			}
			setText(data);
			return this;
		}
	}


	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	public Color getForegroundSelected() {
		return foregroundSelected;
	}

	public void setForegroundSelected(Color foregroundSelected) {
		this.foregroundSelected = foregroundSelected;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getBackgroundSelected() {
		return backgroundSelected;
	}

	public void setBackgroundSelected(Color backgroundSelected) {
		this.backgroundSelected = backgroundSelected;
	}
}
