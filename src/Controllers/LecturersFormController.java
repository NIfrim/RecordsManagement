package Controllers;

import Core.Model;
import Models.Entities.Department;
import View.DefaultElement.DefaultButton;
import View.DefaultElement.DropdownButton;
import View.DefaultElement.DropdownItem;
import View.Section.Lecturers.LecturersTable;
import View.Section.Lecturers.ManageLecturerForm;
import View.Section.Subsection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class LecturersFormController extends FormController
{
    public LecturersFormController(Model lecturersModel)
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
        LecturersTable tableContainer = ((LecturersTable) ((Subsection) mainViewObj).getMainTableContainer());
        ManageLecturerForm form = ((ManageLecturerForm) ((Subsection) mainViewObj).getFormContainer());
        JDialog formWrapper = ((Subsection) mainViewObj).getDialogBox();

        if (source instanceof DefaultButton)
        {
            if (((DefaultButton) source).getName().equals("SaveLecturer")) {
                saveObject("SaveLecturer");
                ((Subsection) mainViewObj).getDialogBox().setVisible(false);
            }
            else if (((DefaultButton) source).getName().equals("Cancel"))
            {
                ((Subsection) mainViewObj).getDialogBox().setVisible(false);
            }
        }

        if (source instanceof JComboBox)
        {

            if (((JComboBox) source).getName().equals("Contract"))
            {
                DropdownButton  selector = ((DropdownButton) source);
                String          selectedOption = ((DropdownItem) selector.getSelectedItem()).getKey();

                switch (selectedOption)
                {
                    case "FullTime":
                        form.addFullTimeLecturerInputs();
                        break;

                    case "PartTime":
                        form.addPartTimeLecturerInputs();
                        break;

                    case "Contract":
                        form.addContractLecturerInputs();
                        break;

                    case "":
                        form.addDefaultInputs();
                        break;
                }

                form.revalidate();
                form.repaint();
            }
        }
    }
}
