package Models.Table;

import Models.Entities.Department;

import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentsTableModel extends ProjectTableModel {

    public DepartmentsTableModel(ArrayList<HashMap<String, Object>> tableData) {
        super(
                new String[]{"\u2610", "ID", "NAME", "TYPE", "URL", "USERNAME", "PASSWORD"},
                tableData
        );
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
    public Object getValueAt(int rowIndex, int columnIndex) {

        Department department = ((Department) this.tableData.get(rowIndex).get("Department"));
        Boolean selected = ((Boolean) this.tableData.get(rowIndex).get("Checkbox"));

        switch (columnIndex) {

            case 0: return selected;
            case 1: return "" + department.getId();
            case 2: return "" + department.getDepartmentName();
            case 3: return "" + department.getDepartmentType();
            case 4: return "" + department.getWebAddress();
            case 5: return "" + department.getUsername();
            case 6: return "" + department.getPassword();
            default: return null;
        }
    }
}
