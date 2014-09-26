package com.vitechinc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class MyTableSortDemo extends JPanel {
	
	private JTable table;
	private JPopupMenu pMenu;
	private JMenuItem menuItem;
	private JDialog dialog;
	private JButton next;
	private JButton cancel;
	private JTextField textField;
	
	public MyTableSortDemo() {
		super(new GridLayout(1,0));
		
		table = new JTable(new MyTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(500,70));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		
		JScrollPane pane = new JScrollPane(table);
		add(pane);
		pMenu = new JPopupMenu();
		menuItem = new JMenuItem("Search");
		dialog = new JDialog();
		next = new JButton("Next");
		cancel = new JButton("Cancel");
		dialog.setSize(300,300);
		dialog.setLayout(new FlowLayout());
		textField = new JTextField(20);
		dialog.add(next);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("search text=" + textField.getText());
				String text = textField.getText();
				TableModel model = table.getModel();
				for(int i=0; i< model.getRowCount(); i++){
					for(int j=0; j< model.getColumnCount(); j++){
						if(!model.getColumnClass(j).equals(text.getClass()))
							continue;
						String s = (String)model.getValueAt(i, j);
						if(s.contains(text)){
							System.out.println("match found at " + i + ", " + j);
							table.setRowSelectionInterval(i, i);
							table.setColumnSelectionInterval(j, j);
							Rectangle r = table.getCellRect(i, j, true);
							table.scrollRectToVisible(r);
							table.repaint();
						}
					}				
				}									
			}
		});
		dialog.add(cancel);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dialog.setVisible(false);
			}
		});
		dialog.add(textField);
		pMenu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){	
				dialog.setVisible(true); 
			 }
		 });
		
		table.addMouseListener( new MouseAdapter() {
			 

			@Override
			public void mousePressed(MouseEvent e) {
				
				System.out.println("mouse clikec: " + e.getButton());
				
				if(e.isPopupTrigger()){
					pMenu.show(e.getComponent(),e.getX() ,e.getY() );
					System.out.println("popup menu");
				}
				
				//e.getButton();
			}
		});
		
	}
	
	class MyTableModel extends AbstractTableModel{
		private String[] columnNames = {"First Name",
	            "Last Name",
	            "Sport",
	            "# of Years",
	            "Vegetarian"};
		private Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
		        };

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.length;
		}
		public String getColumnName(int col) {
            return columnNames[col];
        }
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
		public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
		public void setValueAt(Object value, int row, int col) {

            data[row][col] = value;
            // Normally, one should call fireTableCellUpdated() when 
            // a value is changed.  However, doing so in this demo
            // causes a problem with TableSorter.  The tableChanged()
            // call on TableSorter that results from calling
            // fireTableCellUpdated() causes the indices to be regenerated
            // when they shouldn't be.  Ideally, TableSorter should be
            // given a more intelligent tableChanged() implementation,
            // and then the following line can be uncommented.
            // fireTableCellUpdated(row, col);
        }
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return data[rowIndex][columnIndex];
		}
		
	}
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
		
		MyTableSortDemo demo = new MyTableSortDemo();
		demo.setOpaque(true);
		
		frame.setContentPane(demo);
		frame.pack();
		frame.setVisible(true);
	}

}
