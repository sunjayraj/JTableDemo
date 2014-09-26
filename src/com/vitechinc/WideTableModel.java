package com.vitechinc;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class WideTableModel  extends AbstractTableModel {
    private static final long serialVersionUID = 8798261997256893224L;

    public int getColumnCount() {
        return 7*30;
    }

    public int getRowCount() {
        return 10000;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public Class<?> getCellClassAt(int row, int column) {
        return getColumnClass(column);
    }

    @Override
    public String getColumnName(int column) {
    	if(column<30){
            return "int column" + " " + column;
    	}
    	else if(column<60){
            return "Dbl colm" + " " + column;
    	}
    	else if(column<90){
            return "bool colm" + " " + column;
    	}
    	else if(column<120){
            return "str colm" + " " + column;
    	}
    	else if(column<150){
            return "istr colm" + " " + column;
    	}
    	else if(column<180){
            return "date colm" + " " + column;
    	}
    	else if(column<210){
            return "long colm" + " " + column;
    	}
    	return "";
    }

    @Override
    public Class<?> getColumnClass(int column) {
    	if(column<30){
            return Integer.class;
    	}
    	else if(column<60){
            return Double.class;
    	}
    	else if(column<90){
            return Boolean.class;
    	}
    	else if(column<120){
            return String.class;
    	}
    	else if(column<150){
            return String.class;//Icon.class;
    	}
    	else if(column<180){
            return Date.class;
    	}
    	else if(column<210){
            return Long.class;
    	}
        return Object.class;
    }

    public boolean isColumnDraggable(int columnIndex) {
        return columnIndex != 0;
    }

    public Object getValueAt(int row, int column) {
    	if(column<30){
                return 100000*column + row;
    	}
    	else if(column<60){
            return row * 2.333333;
    	}
    	else if(column<90){
            if (row % 2 == 0)
                return Boolean.TRUE;
            return Boolean.FALSE;
    	}
    	else if(column<120){
            return "row " + (getRowCount() - row) + " ,column=" + column;
    	}
    	else if(column<150){
            return "irow " + (getRowCount() - row);
    	}
    	else if(column<180){
            Calendar calendar = Calendar.getInstance();
            calendar.roll(Calendar.DAY_OF_YEAR, row);
            return calendar.getTime();
    	}
    	else if(column<210){
            return row * 10 + 900000000000000000L;
    	}
        return null;
    }

    public String getToolTipText(int columnIndex) {
        return "Click to sort this " + getColumnName(columnIndex);
    }
}