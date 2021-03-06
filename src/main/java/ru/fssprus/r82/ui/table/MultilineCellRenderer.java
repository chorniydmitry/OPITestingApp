package ru.fssprus.r82.ui.table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class MultilineCellRenderer extends JTextArea implements TableCellRenderer {
        
        private static final long serialVersionUID = 1L;
        
        public MultilineCellRenderer() {
        
            super();
            setLineWrap(true);
            setWrapStyleWord(true);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object arg1, boolean isSelected, boolean hasFocus, int row, int column) {
        
            String data = (String) arg1.toString();
            int lineWidth = this.getFontMetrics(this.getFont()).stringWidth(data);
            int lineHeight = this.getFontMetrics(this.getFont()).getHeight();
            int rowWidth = table.getCellRect(row, column, true).width;
          
            int newRowHeight = (int) ((lineWidth / rowWidth) * (lineHeight)) + lineHeight * 2;
            if (table.getRowHeight(row) != newRowHeight) {
                table.setRowHeight(row, newRowHeight);
                }
            this.setText(data);
            return this;
        }


}
