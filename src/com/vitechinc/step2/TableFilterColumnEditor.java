package com.vitechinc.step2;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public class TableFilterColumnEditor extends JPanel {
    private JTable table;
    private JTextField statusText;
    private JTextField filterText;
    private TableRowSorter<DataModelWithVO> sorter;

    public TableFilterColumnEditor() {
        //super(new GridLayout(1,0));
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        DataModelWithVO model = new DataModelWithVO();
        table = new JTable(model){
            @Override
            public Dimension getPreferredScrollableViewportSize()
            { //only adjust the table's width to lower values
                Dimension size = super.getPreferredScrollableViewportSize();
                return new Dimension(Math.min(getPreferredSize().width, size.width), size.height);
            }
        };
        
        //table.setPreferredScrollableViewportSize(new Dimension(500,70)); //hard coded
        //table.setPreferredScrollableViewportSize(table.getPreferredSize()); // can lead to huge table heights if number of rows is large
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Horizontal scrolling
        table.setFillsViewportHeight(true);
        
        JScrollPane pane = new JScrollPane(table);
        add(pane);

        table.setDefaultEditor(Integer.class, new QuietIntegerEditor(0,100));
        //If we didn't want this editor to be used for other Integer columns, we'd do this:
	        //table.getColumnModel().getColumn(3).setCellEditor(new IntegerEditor(0, 100));
        
        sorter = new TableRowSorter<DataModelWithVO>(model);
        table.setRowSorter(sorter);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int viewRow = table.getSelectedRow();
                        if(viewRow<0){
                            statusText.setText("");
                        } else {
                            int modelRow = table.convertRowIndexToModel(viewRow);
                            statusText.setText(
                                    String.format("Selected Row in view: %d. " +
                                            "Selected Row in model: %d.",
                                            viewRow, modelRow));
                            System.out.println("Selected Row in view: " + viewRow +
                                            ". Selected Row in model: " + modelRow);
                        }
                    }                    
                });
        
        
        JPanel form = new JPanel(new SpringLayout());
        JLabel ll = new JLabel("Filter Text:", SwingConstants.TRAILING);
        form.add(ll);
        
        filterText = new JTextField();
        filterText.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }            
        });
        
        ll.setLabelFor(filterText);
        form.add(filterText);
        
        JLabel l2 = new JLabel("Status: ", SwingConstants.TRAILING);
        form.add(l2);
        
        statusText = new JTextField();
        l2.setLabelFor(statusText);
        form.add(statusText);
        makeCompactGrid(form, 2, 2, 6, 6, 6, 6);
        //SpringUtilities
        add(form);
    }
    private void newFilter() {
        RowFilter<DataModelWithVO,Object> rf = null;
        try{
            rf = RowFilter.regexFilter(filterText.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TableFilterColumnEditor demo = new TableFilterColumnEditor();
        demo.setOpaque(true);
        frame.setContentPane(demo);
        
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });
    }

    /**
     * Aligns the first <code>rows</code> * <code>cols</code>
     * components of <code>parent</code> in
     * a grid. Each component in a column is as wide as the maximum
     * preferred width of the components in that column;
     * height is similarly determined for each row.
     * The parent is made just big enough to fit them all.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param initialX x location to start the grid at
     * @param initialY y location to start the grid at
     * @param xPad x padding between cells
     * @param yPad y padding between cells
     */
    public static void makeCompactGrid(Container parent,
                                       int rows, int cols,
                                       int initialX, int initialY,
                                       int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }
 
        //Align all cells in each column and make them the same width.
        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width,
                                   getConstraintsForCell(r, c, parent, cols).
                                       getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }
 
        //Align all cells in each row and make them the same height.
        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height,
                                    getConstraintsForCell(r, c, parent, cols).
                                        getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }
 
        //Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }
    private static SpringLayout.Constraints getConstraintsForCell(
            int row, int col,
            Container parent,
            int cols) 
    {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }
}