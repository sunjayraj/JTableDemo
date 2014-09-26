package com.vitechinc;

import java.awt.Dimension;

//import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//import com.vitechinc.MyTableModel;

public class Revison extends JPanel{
	
	private JTable table;
	private JScrollPane pane;
	public Revison(){
	//	super(new GridLayout(1,0));
		
		table = new JTable();//
		table.setModel(new com.vitechinc.MyTableModel() );
		table.setPreferredScrollableViewportSize(new Dimension(500,70));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		pane = new JScrollPane(table);
		add(pane);

	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndStartGui();
			}

			private void createAndStartGui() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
				Revison demo = new Revison();
				demo.setOpaque(true);
				frame.setContentPane(demo);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}
