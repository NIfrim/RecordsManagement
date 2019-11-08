package View.DefaultElement;

import Controllers.TableController;
import Core.Model;
import Models.Table.ProjectTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;

public abstract class DefaultTable extends JPanel {

    protected Model dataModel;
    protected ProjectTableModel tableModel;

    protected JScrollPane jsp;
    protected JTable table;

    protected TableController tableController;

    public DefaultTable(TableController tableController, Model dataModel, ProjectTableModel tableModel) {

        this.dataModel = dataModel;
        this.tableModel = tableModel;
        this.tableController = tableController;

        // Table which has horizontal scrollbar but also keeps the parent container width when resizing window.
        // Also setting windows 10 look and feel for the table.
        LookAndFeel defaultLAF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            table = new JTable(this.tableModel){
                public boolean getScrollableTracksViewportWidth()
                {
                    return getPreferredSize().width < getParent().getWidth();
                }
            };
            jsp = new JScrollPane(table);

            SwingUtilities.updateComponentTreeUI(table);
            SwingUtilities.updateComponentTreeUI(jsp);

            UIManager.setLookAndFeel(defaultLAF);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setColumnHeaderView(table.getTableHeader());

        setDefaultStyle();
        addDefaultElements();
        addSelectAllListener();
    }

    private void addSelectAllListener() {

        // Get the first column and check if the header is a string which stores a boolean value
        TableColumn checkboxColumn = table.getColumnModel().getColumn(0);
        String columnValue = checkboxColumn.getHeaderValue().toString();

        // u2610 is ☐ in unicode
        // u2611 is ☑ in unicode
        if (columnValue.equals("\u2610") || columnValue.equals("\u2611")) {
            table.getTableHeader().addMouseListener(tableController);
        }
    }

    private void setDefaultStyle() {

        setLayout(new BorderLayout());
        setOpaque(false);

        table.setFillsViewportHeight(true);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);
        table.setAlignmentX(JTable.CENTER_ALIGNMENT);

        int columns = table.getColumnCount();

        // Set the columns widths to match their column names
        for (int i = 1; i < columns ; i++)
        {
            TableColumn tCol = table.getColumnModel().getColumn(i);
            String columnName = tableModel.getColumnName(i);

            tCol.setMinWidth(columnName.length() * 8);
        }

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 25));
        table.getTableHeader().setOpaque(true);

        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        table.getTableHeader().setBackground(Color.ORANGE);
    }

    private void addDefaultElements() {
        add(jsp, BorderLayout.CENTER);
    }

    public ProjectTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public TableController getTableController() {
        return tableController;
    }
}
