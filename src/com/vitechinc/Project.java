package com.vitechinc;

import java.awt.Dimension;
import java.awt.GridLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Project extends JPanel{
	
	public static void main(String[] args) {	
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndStartGui();
			}
			
		});
	}
	private static void createAndStartGui() {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Project proj = new Project();
	proj.setOpaque(true);
	frame.setContentPane(proj);
	frame.pack();
	frame.setVisible(true);
		
	}
	private JTable table;
	public Project(){
		super(new GridLayout(1,0));
		table = new JTable(new MyDatabaseModel());
		table.setPreferredScrollableViewportSize(new Dimension(500,70));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		JScrollPane pane = new JScrollPane(table);
		add(pane);
	}

}
class MyDatabaseModel implements TableModel{
	static String database = "/Users/sanjayrajsoni/Downloads/hsqldb/demo";
	public static void main(String[]args)throws Exception{
		MyDatabaseModel db = new MyDatabaseModel();
		
	}
	private static Connection getDbConnection() throws ClassNotFoundException,
	SQLException {
		//1. load DB driver
		Class.forName("org.hsqldb.jdbcDriver");

		// 2. setup jdbc url
		String jdbcUrl = "jdbc:hsqldb:file:" + database;

		// 3. get DB conx 
		return DriverManager.getConnection(jdbcUrl, "sa", "");
	}
	 void populatePeopleTable()throws SQLException{
		 Connection connection = null;
		 Statement statement = null;
		 try {
			connection = getDbConnection();
			statement = connection.createStatement();
			statement.executeUpdate("Drop table if exist people");
			statement.executeUpdate("Create table people(Firstname Varchar,Lastname Varchar,Sport " +
					"Varchar, Age Numeric,Vegetarian Boolean");
		PreparedStatement prep = connection.prepareStatement("Insert into people Values(?,?,?,?,?)");
		addRowToBatch(prep,"Prayom","Soni","Cricket",7,false);
		addRowToBatch(prep,"Ishika","Soni","Basketball",10,false);
		addRowToBatch(prep,"Sanjay","Raj","Running",43,true);
		
		connection.setAutoCommit(false);
		prep.executeBatch();
		connection.setAutoCommit(true);
		
		 } catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if( statement!=null){
		
			statement.close();
			}
			if(connection!=null){
				connection.close();
			}
		}
	 }
	 void addRowToBatch(PreparedStatement prep,String F_name,String L_name,String sport,int age,
			 				boolean vegetarian) throws SQLException {
		prep.setString(1,F_name );
		prep.setString(2,L_name);
		prep.setString(3, sport);
		prep.setInt(4,age);
		prep.setBoolean(5, vegetarian);
		
	}
	 void readPeopleTable(String tableName){
		 ResultSet  resultSet = null;
		 Connection connection = null;
		 Statement statement = null;
		String sql = " Select * From People" ;
		
		try {
			connection = getDbConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				System.out.println(resultSet.getString(1) +","+resultSet.getString(2) +","+
			resultSet.getString(3)+","+resultSet.getInt(4)+","+resultSet.getBoolean(5));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}finally {
			
				try {
					
					if(resultSet != null){
						resultSet.close();
					}
					if(statement !=null){
						statement.close();
					}
					if(connection!= null){
						connection.close();
					}
				}
				
				catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
}
class PeopleTableRowDTO{
	private String firstName;
	private String lastName;
	private String sport;
	private int age;
	private boolean vegetarian;
	public String getFirstName() {
		return firstName;
		
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isVegetarian() {
		return vegetarian;
	}
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
}
class PeopleTableDTO{
	private int numOfRows;
	private int numOfColumns;
	private String[] columnNames;
	private Class[] columnClasses;
	private List<PeopleTableRowDTO> rowList;
	
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public int getNumOfColumns() {
		return numOfColumns;
	}
	public void setNumOfColumns(int numOfColumns) {
		this.numOfColumns = numOfColumns;
	}
	public String[] getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	public Class[] getColumnClasses() {
		return columnClasses;
	}
	public void setColumnClasses(Class[] columnClasses) {
		this.columnClasses = columnClasses;
	}
	public List<PeopleTableRowDTO> getRowList() {
		return rowList;
	}
	public void setRowList(List<PeopleTableRowDTO> rowList) {
		this.rowList = rowList;
	}
}

class MyTableModel extends AbstractTableModel{
/**
 * 1. get all the rows in the ctor and populate the data array.
 * 2. at a time just show 20 rows only - make another database call: how to do this?
 *  Ans: in the db call include the beginning and the ending row (start=21, end=40).
 * 3.	
 */
	private String[] coloumnNames = {
		"FirstName",
		"LastName",
		"Sport",
		"#OfYears",
		"Vegetarian"
	};
	private Object[][] data = {
			{"Prayom","Soni","Cricket",new Integer(7),new Boolean(false)},
			{"Ishika","Soni","Basketball",new Integer(10),new Boolean(false)},
			{"Sanjay","Raj","Running",new Integer(43),new Boolean(false)},
	};
	
	@Override
	public int getRowCount() {	
		return data.length;
	}
	@Override
	public int getColumnCount() {
		return coloumnNames.length;
	}
	@Override
	public String getColumnName(int col) {
		return coloumnNames[col];
	}
	@Override
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	@Override
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
	@Override
	public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}