package Controllers;

import Core.Controller;
import Core.Model;
import Models.DepartmentsModel;
import Models.Entities.Department;
import Models.Entities.Lecturer;
import Models.LecturersModel;
import Models.Table.ProjectTableModel;
import View.DefaultElement.DefaultTable;
import View.Section.Departments.DepartmentsSearchSubsection;
import View.Section.Lecturers.LecturerSearchSubsection;
import View.Section.PopupWindow;
import View.Section.Subsection;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class TableController extends Controller implements MouseListener {


    public TableController(Model dataModel)
    {
        super(dataModel);
        this.viewContainers = new HashMap<String, Container>();
        this.viewComponents = new HashMap<String, Component>();
        this.modelObjects = new HashMap<String, Model>();
    }


    public ArrayList<HashMap<String, Object>> getSelectedRows(DefaultTable tableContainer)
    {
        JTable table = tableContainer.getTable();
        ProjectTableModel tableModel = tableContainer.getTableModel();

        ArrayList<HashMap<String, Object>> selectedRows = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++)
        {
            if (((Boolean) tableModel.getValueAt(i, 0)))
                selectedRows.add(tableModel.getTableData().get(i));
        }

        return selectedRows;
    }


    public void removeSelectedRows(ArrayList<String> idsList)
    {
        DefaultTable tableContainer = ((Subsection) mainViewObj).getMainTableContainer();
        ArrayList<HashMap<String, Object>> tableData = tableContainer.getTableModel().getTableData();

        for (HashMap row : tableData)
        {
            if (row.containsKey("Lecturer"))
            {
                String selected = String.valueOf(row.get("Checkbox"));
                if (selected.equals("true"))
                {
                    ((LecturersModel) mainDataModel).removeLecturer(((Lecturer) row.get("Lecturer")).getId());
                }
            }
            else if (row.containsKey("Department"))
            {
                if (row.get("Checkbox").equals(true))
                {
                    ((DepartmentsModel) mainDataModel).removeDepartment(((Department) row.get("Department")).getId());
                }
            }
        }
    }

    public void refresh(DefaultTable tableContainer, String filterName, String filterValue)
    {
        tableContainer.getTableModel().setTableData(this.getTableData(filterName, filterValue));
        tableContainer.getTable().revalidate();
        tableContainer.getTable().repaint();
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public ArrayList<HashMap<String, Object>> getTableData(String filterName, String filterValue)
    {
        ArrayList<HashMap<String, Object>> tableData = new ArrayList<>();

        if (mainDataModel instanceof LecturersModel)
        {
            HashMap<String, Lecturer> lecturersFound = mainDataModel.getLecturersList();

            for (String key : lecturersFound.keySet())
            {
                if (filterValue == null || filterValue.equals("-1"))
                {
                    HashMap<String, Object> foundRows = getUnfilteredRows(lecturersFound.get(key));
                    if (foundRows.size() > 0)
                        tableData.add(foundRows);
                }
                else
                {
                    HashMap<String, Object> newRow = getFilteredRows(lecturersFound.get(key), filterName, filterValue);
                    if (newRow.size() > 0)
                        tableData.add(newRow);
                }
            }
        }
        else if (mainDataModel instanceof DepartmentsModel)
        {
            HashMap<String, Department> departmentsFound = mainDataModel.getDepartmentsList();

            for (String key : departmentsFound.keySet())
            {
                if (filterValue == null || filterValue.equals("-1"))
                {
                    HashMap<String, Object> foundRows = getUnfilteredRows(departmentsFound.get(key));
                    if (foundRows.size() > 0)
                        tableData.add(foundRows);
                }
                else
                {
                    HashMap<String, Object> newRow = getFilteredRows(departmentsFound.get(key), filterName, filterValue);
                    if (newRow.size() > 0)
                        tableData.add(newRow);
                }
            }
        }

        return tableData;
    }


    private void selectAll(TableColumn tableColumn) {
        // Switch the header value to ticked, u2611 is ☑ in unicode
        tableColumn.setHeaderValue("\u2611");

        ProjectTableModel tableModel = null;

        if (mainViewObj instanceof LecturerSearchSubsection)
        {
            tableModel = ((LecturerSearchSubsection)mainViewObj).getMainTableContainer().getTableModel();
        }
        else if (mainViewObj instanceof DepartmentsSearchSubsection)
        {
            tableModel = ((DepartmentsSearchSubsection)mainViewObj).getMainTableContainer().getTableModel();
        }


        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            tableModel.setValueAt(true, i, 0);
        }
    }

    private void unselectAll(TableColumn tableColumn) {
        // Switch the header value to un-ticked, u2610 is ☐ in unicode
        tableColumn.setHeaderValue("\u2610");

        ProjectTableModel tableModel = ((Subsection) mainViewObj).getMainTableContainer().getTableModel();
        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            tableModel.setValueAt(false, i, 0);
        }
    }

    private HashMap<String, Object> getUnfilteredRows(Model dataModel)
    {
        HashMap<String, Object> row = new HashMap<>();

        if (dataModel instanceof Lecturer)
        {
            if (((Department) getModelObjects().get("Department")).getDepartmentType().equals("Administration"))
            {
                row.put("Checkbox", false);
                row.put("Lecturer", dataModel);
            }
            else if (((Lecturer) dataModel).getDepartment().equals(((Department) getModelObjects().get("Department")).getDepartmentName()))
            {
                row.put("Checkbox", false);
                row.put("Lecturer", dataModel);
            }
        }
        else if (dataModel instanceof Department)
        {
            row.put("Checkbox", false);
            row.put("Department", dataModel);
        }

        return row;
    }

    private HashMap<String, Object> getFilteredRows(Model dataModel, String filterName, String filterValue)
    {
        HashMap<String, Object> row = new HashMap<>();
        String currValue = "";

        try {
            Class<?> dataModelType = dataModel.getClass();
            Method filterMethod = dataModelType.getMethod("get" + filterName);
            currValue = String.valueOf(filterMethod.invoke(dataModel)).toLowerCase();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (currValue.contains(filterValue.toLowerCase()))
        {
            if (dataModel instanceof Lecturer)
            {
                if (((Subsection) mainViewObj).getDepartmentLoggedIn().getDepartmentType().equals("Administration"))
                {
                    row.put("Checkbox", Boolean.FALSE);
                    row.put("Lecturer", dataModel);
                }
                else if (((Lecturer) dataModel).getDepartment().equals(((Subsection) mainViewObj).getDepartmentLoggedIn().getDepartmentName()))
                {
                    row.put("Checkbox", Boolean.FALSE);
                    row.put("Lecturer", dataModel);
                }
            }
            else if (dataModel instanceof Department)
            {
                row.put("Checkbox", Boolean.FALSE);
                row.put("Department", dataModel);
            }
        }

        return row;
    }



    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        Object source = e.getSource();

        if (source instanceof JTableHeader)
        {
            JTable table = ((Subsection) mainViewObj).getMainTableContainer().getTable();

            // Store the clicked column number
            int columnIndex = table.columnAtPoint(e.getPoint());
            TableColumn tableColumn = table.getColumnModel().getColumn(columnIndex);
            String columnVal = tableColumn.getHeaderValue().toString();

            if (columnVal.equals("\u2610"))
            {
                selectAll(tableColumn);
            }
            else if (columnVal.equals("\u2611"))
            {
                unselectAll(tableColumn);
            }
        }
        else if (source instanceof JTable)
        {
            JTable table = ((Subsection) mainViewObj).getMainTableContainer().getTable();

            if (table.getSelectedRow() != -1 && table.getSelectedColumn() > 0)
            {
                PopupWindow form = ((Subsection) mainViewObj).getFormContainer();
                JButton addBtn = ((Subsection) mainViewObj).getAddButton();

                int clickedRow = table.getSelectedRow();

                addBtn.doClick();
                ((FormController) form.getMainController()).setFormFields(form, clickedRow);
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
