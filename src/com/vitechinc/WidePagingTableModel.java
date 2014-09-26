package com.vitechinc;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class WidePagingTableModel  extends AbstractTableModel implements PagingModel {
    private static final long serialVersionUID = 8798261997256893224L;

    private int PAGE_SIZE = 100;
	private int MAX_PAGE = 3;
	private int currentPage = 0;

	public void nextPage(){
		if(this.currentPage==this.MAX_PAGE){
			return;
		}
		this.currentPage++;
		System.out.println("currPage=" + this.currentPage);
	}
	public void prevPage(){
		if(this.currentPage==0){
			return;
		}
		this.currentPage--;
		System.out.println("currPage=" + this.currentPage);
	}
	public int getColumnCount() {
        return 7*30;
    }

    public int getRowCount() {
        return this.PAGE_SIZE; //10000;
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
            return "double column" + " " + column;
    	}
    	else if(column<90){
            return "boolean column" + " " + column;
    	}
    	else if(column<120){
            return "string column" + " " + column;
    	}
    	else if(column<150){
            return "istring column" + " " + column;
    	}
    	else if(column<180){
            return "date column" + " " + column;
    	}
    	else if(column<210){
            return "long column" + " " + column;
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
    	row = this.currentPage * this.PAGE_SIZE + row;
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
            return "row " + ((MAX_PAGE+1) * this.PAGE_SIZE - row) + " ,column=" + column;
    	}
    	else if(column<150){
            return "irow " + ((MAX_PAGE+1) * this.PAGE_SIZE - row);
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