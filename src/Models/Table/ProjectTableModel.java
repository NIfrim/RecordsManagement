package Models.Table;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ProjectTableModel extends AbstractTableModel {

    private String[] columnNames;
    protected ArrayList<HashMap<String, Object>> tableData = new ArrayList<>();

    public ProjectTableModel(String[] columnNames, ArrayList<HashMap<String, Object>> tableData) {

        this.columnNames = columnNames;
        this.tableData.addAll(tableData);
    }

    /**
     * Function to add a row to the table data,
     * after that it refreshes the table to display the new row
     * */
    public void addRow(HashMap<String, Object> newRow) {
        tableData.add(newRow);
        fireTableDataChanged();
    }

    public void addRows(ArrayList<HashMap<String, Object>> rowsList) {
        tableData.addAll(rowsList);
        fireTableDataChanged();
    }

    /**
     * Returns a Hash Map with the table data as rows,
     * The table data stores data under 2 String keys:
     *      - Models.File Entities name (e.g. "Staff")
     *      - Action Button name (e.g. "EditStaffBtn")
     * */
    public ArrayList<HashMap<String, Object>> getTableData() {
        return tableData;
    }
    public void setTableData(ArrayList<HashMap<String, Object>> newTableData)
    {
        this.tableData = newTableData;
        fireTableDataChanged();
    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return tableData.size();
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the name of the column at <code>columnIndex</code>.  This is used
     * to initialize the table's column header name.  Note: this name does
     * not need to be unique; two columns in a table can have the same name.
     *
     * @param columnIndex the index of the column
     * @return the name of the column
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * Returns the most specific superclass for all the cell values
     * in the column.  This is used by the <code>JTable</code> to set up a
     * default renderer and editor for the column.
     *
     * @param columnIndex the index of the column
     * @return the common ancestor class of the object values in the model.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    /**
     * Returns true if the cell at <code>rowIndex</code> and
     * <code>columnIndex</code>
     * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
     * change the value of that cell.
     *
     * @param rowIndex    the row whose value to be queried
     * @param columnIndex the column whose value to be queried
     * @return true if the cell is editable
     * @see #setValueAt
     *
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 && tableData.get(rowIndex).containsKey("Checkbox");
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Entities at the specified cell
     */
    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);

    /**
     * Sets the value in the cell at <code>columnIndex</code> and
     * <code>rowIndex</code> to <code>aValue</code>.
     *
     * @param aValue      the new value
     * @param rowIndex    the row whose value is to be changed
     * @param columnIndex the column whose value is to be changed
     * @see #getValueAt
     * @see #isCellEditable
     *
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tableData.get(rowIndex).replace("Checkbox", aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param l the TableModelListener
     */
    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    /**
     * Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param l the TableModelListener
     */
    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
