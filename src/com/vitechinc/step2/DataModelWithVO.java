package com.vitechinc.step2;

import javax.swing.table.AbstractTableModel;

public class DataModelWithVO extends AbstractTableModel{

    private static final long serialVersionUID = 1L;
    private String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
    public static final int FIRST_NAME_INDEX = 0;
    public static final int LAST_NAME_INDEX = 1;
    public static final int SPORT_INDEX = 2;
    public static final int NUM_YEARS_INDEX = 3;
    public static final int VEGETARIAN_INDEX = 4;
    
    private User[] data = {
            new User("Kathy", "Smith",
             "Snowboarding", new Integer(5), new Boolean(false)),
             new User("John", "Doe",
             "Rowing", new Integer(3), new Boolean(true)),
             new User("Sue", "Black",
             "Knitting", new Integer(2), new Boolean(false)),
             new User("Jane", "White",
             "Speed reading", new Integer(20), new Boolean(true)),
             new User("Joe", "Brown",
             "Pool", new Integer(10), new Boolean(false))
            };
    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    @Override
    public int getRowCount() {
        return data.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = data[rowIndex];
        
        switch(columnIndex){
            case FIRST_NAME_INDEX:
                return u.getFirstName();
            case LAST_NAME_INDEX:
                return u.getLastName();
            case SPORT_INDEX:
                return u.getSport();
            case NUM_YEARS_INDEX:
                return u.getNumOfYears();
            case VEGETARIAN_INDEX:
                return u.getVegetarian();
            default:
                return new Object();
        }
    }    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    public Class getColumnClass(int c) {
        Class r = null;
        switch(c){
            case FIRST_NAME_INDEX:
            case LAST_NAME_INDEX:
            case SPORT_INDEX:
                r = String.class;
                break;
            case NUM_YEARS_INDEX:
                r = Integer.class; break;
            case VEGETARIAN_INDEX:
                r = Boolean.class; break;
            default:
                r = Object.class; break;
        }
        return r;
    }
    public boolean isCellEditable(int row, int col) {
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
    public void setValueAt(Object value, int row, int col) {
        User u = data[row];
        
        switch(col){
            case FIRST_NAME_INDEX:
                u.setFirstName((String)value); break;
            case LAST_NAME_INDEX:
                u.setLastName((String)value); break;
            case SPORT_INDEX:
                u.setSport((String)value); break;
            case NUM_YEARS_INDEX:
                u.setNumOfYears((Integer)value); break;
            case VEGETARIAN_INDEX:
                u.setVegetarian((Boolean)value); break;
            default:
                System.out.println("invalid index");
        }        
        fireTableCellUpdated(row, col);
    }    
}