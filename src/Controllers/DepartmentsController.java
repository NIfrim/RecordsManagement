package Controllers;

import Core.Controller;
import Core.Model;
import Models.Entities.Department;
import View.DefaultElement.DefaultButton;
import View.DefaultElement.DefaultTable;
import View.DefaultElement.DropdownItem;
import View.Section.Departments.DepartmentsSection;
import View.Section.Departments.ManageDepartmentForm;
import View.Section.PopupWindow;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentsController extends Controller implements KeyListener {

    public DepartmentsController(Model dataModel) {
        super(dataModel);
    }

    protected void deleteSelected()
    {
        DefaultTable tableContainer = ((DepartmentsSection) mainViewObj).getDepartmentsSearchSubsection().getMainTableContainer();

        ArrayList<HashMap<String, Object>> selectedRows = tableContainer.getTableController().getSelectedRows(tableContainer);
        ArrayList<String> departmentsIds = new ArrayList<>();

        // Remove the objects from the array with data models
        for (HashMap<String, Object> row : selectedRows)
        {
            departmentsIds.add(((Department) row.get("Department")).getId());
        }

        if (departmentsIds.size() > 0)
        {
            tableContainer.getTableController().removeSelectedRows(departmentsIds);
            mainDataModel.removeRelatedFiles("src\\Files\\Departments\\", departmentsIds);
            tableContainer.getTableController().refresh(tableContainer, "DepartmentType", "-1");
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        DefaultTable tableContainer = (((DepartmentsSection) mainViewObj).getDepartmentsSearchSubsection().getMainTableContainer());

        if (source instanceof JButton)
        {
            if (((DefaultButton) source).getName().equals("ManageDepartment"))
            {
                PopupWindow formContainer = ((DepartmentsSection) mainViewObj).getDepartmentsSearchSubsection().getFormContainer();
                ((ManageDepartmentForm) formContainer).generateId();

                showForm("ManageDepartment");
            }
            else if (((DefaultButton) source).getName().equals("RemoveSelected"))
            {
                deleteSelected();
            }
        }
        else if (source instanceof JComboBox)
        {
            tableContainer.getTableController().refresh(tableContainer, ((JComboBox) source).getName(), ((DropdownItem) ((JComboBox) source).getSelectedItem()).getKey());
        }
        else if (source instanceof JTextComponent)
        {
            tableContainer.getTableController().refresh(tableContainer, ((JTextComponent) source).getName(), ((JTextComponent) source).getText());
        }
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        Object source = e.getSource();

        DefaultTable tableContainer = (((DepartmentsSection) mainViewObj).getDepartmentsSearchSubsection().getMainTableContainer());

        if (source instanceof JTextComponent)
        {
            tableContainer.getTableController().refresh(tableContainer, ((JTextComponent) source).getName(), ((JTextComponent) source).getText());
        }
    }
}
