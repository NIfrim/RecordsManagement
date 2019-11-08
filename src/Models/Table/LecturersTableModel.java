package Models.Table;

import Models.Entities.ContractLecturer;
import Models.Entities.FullTimeLecturer;
import Models.Entities.Lecturer;
import Models.Entities.PartTimeLecturer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class LecturersTableModel extends ProjectTableModel {

    public LecturersTableModel(ArrayList<HashMap<String, Object>> tableData) {
        super(
                new String[]{"\u2610", "ID", "DEPARTMENT", "NAME", "EMAIL", "ADDRESS", "MOBILE", "CONTRACT TYPE", "STARTED", "CONTRACT START", "CONTRACT END", "SALARY", "PAY/HR"},
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

        Lecturer person = null;
        Boolean selected = null;

        if (tableData.size() > 0)
        {
            person = ((Lecturer) this.tableData.get(rowIndex).get("Lecturer"));
            selected = (Boolean) this.tableData.get(rowIndex).get("Checkbox");
        }


        if (person != null)
        {
            switch (columnIndex) {

                case 0: return (selected != null) ? selected : "";
                case 1: return "" + person.getId();
                case 2: return "" + person.getDepartment();
                case 3: return "" + person.getFullName();
                case 4: return "" + person.getEmail();
                case 5: return "" + person.getAddress();
                case 6: return "" + person.getContactNumber();
                case 7: return "" + person.getContract();
                case 8: return "" + person.getStatus();
                case 9: return "" + person.getStartDate();
                case 10: return "" + getEndDate(rowIndex);
                case 11: return "" + getSalary(rowIndex);
                case 12: return "" + getHourlyPay(rowIndex);
                default: return null;
            }
        }
        else return "";

    }

    private Object getHourlyPay(int row) {

        Object person = this.tableData.get(row).get("Lecturer");

        if (person.getClass() == PartTimeLecturer.class)
            return "£ " + ((PartTimeLecturer) person).getHourlyRate();

        return "";
    }

    private Object getSalary(int row) {

        Object person = this.tableData.get(row).get("Lecturer");

        if (person.getClass() == FullTimeLecturer.class || person.getClass() == ContractLecturer.class)
        {
            if (person.getClass() == FullTimeLecturer.class)
                return "£ " + ((FullTimeLecturer) person).getSalary();

            return "£ " + ((ContractLecturer) person).getSalary();
        }
        else {
            return "";
        }
    }

    private String getEndDate(int row) {

        Object person = this.tableData.get(row).get("Lecturer");

        if (person.getClass() == ContractLecturer.class) {
            return ((ContractLecturer) person).getEndDate();
        }
        else {
            return "";
        }
    }
}
