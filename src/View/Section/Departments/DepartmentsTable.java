package View.Section.Departments;

import Controllers.TableController;
import Core.Model;
import Models.Table.ProjectTableModel;
import View.DefaultElement.DefaultTable;

import javax.swing.table.TableColumn;

public class DepartmentsTable extends DefaultTable {

    DepartmentsTable(TableController tableController, Model dataModel, ProjectTableModel tableModel) {

        super(tableController, dataModel, tableModel);

        styleTable();
    }

    private void styleTable() {
        // Set minimum and maximum column width;
        TableColumn column = table.getColumnModel().getColumn(0);

        column.setMinWidth(30);
        column.setMaxWidth(30);
    }
}
