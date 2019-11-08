package Controllers;

import Core.Model;
import Models.Entities.Department;
import View.DefaultElement.DefaultButton;
import View.DefaultElement.DropdownButton;
import View.DefaultElement.InputWPlaceholder;
import View.Section.Departments.DepartmentsTable;
import View.Section.Departments.ManageDepartmentForm;
import View.Section.Subsection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class DepartmentsFormController extends FormController
{
    public DepartmentsFormController(Model lecturersModel)
    {
        super(lecturersModel);
    }


    public ArrayList<Department> getDepartments()
    {
        ArrayList<Department> departmentsList = new ArrayList<>();

        ArrayList<File> files = getFiles("src\\Files\\Departments");

        for (File file : files)
        {
            Object department = new Department().fromFile(file);

            departmentsList.add(((Department) department));
        }

        return departmentsList;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        DepartmentsTable tableContainer = ((DepartmentsTable) ((Subsection) mainViewObj).getMainTableContainer());
        ManageDepartmentForm form = ((ManageDepartmentForm) ((Subsection) mainViewObj).getFormContainer());

        if (source instanceof DefaultButton)
        {
            if (((DefaultButton) source).getName().equals("SaveDepartment")) {
                saveObject("SaveDepartment");
                ((Subsection) mainViewObj).getDialogBox().setVisible(false);
            }
            else if (((DefaultButton) source).getName().equals("NewType"))
            {
                InputWPlaceholder textInput = ((ManageDepartmentForm) ((Subsection) mainViewObj).getFormContainer()).getTypeInputField();
                DropdownButton typeSelector = ((ManageDepartmentForm) ((Subsection) mainViewObj).getFormContainer()).getTypeSelector();

                typeSelector.addDropdownItem("DepartmentType", textInput.getText(), typeSelector);
            }
            else if (((DefaultButton) source).getName().equals("Cancel"))
            {
                ((Subsection) mainViewObj).getDialogBox().setVisible(false);
            }
        }

        if (source instanceof JComboBox)
        {

        }
    }
}
