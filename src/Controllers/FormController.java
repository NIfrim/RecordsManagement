package Controllers;

import Core.Controller;
import Core.Model;
import Models.DepartmentsModel;
import Models.Entities.*;
import Models.LecturersModel;
import View.DefaultElement.DefaultTable;
import View.DefaultElement.DropdownButton;
import View.DefaultElement.DropdownItem;
import View.Section.Departments.ManageDepartmentForm;
import View.Section.Lecturers.ManageLecturerForm;
import View.Section.PopupWindow;
import View.Section.Subsection;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class FormController extends Controller {

    protected PopupWindow visibleForm;
    protected JDialog formContainer;
    protected HashMap<String, Object> formData;

    public FormController(Model dataModel)
    {
        super(dataModel);
    }

    public void setFormFields(PopupWindow form, int clickedRow)
    {
        HashMap<String, Object> formInputs = new HashMap<>();

        Object dataModel = null;

        if (form instanceof ManageLecturerForm)
        {
            dataModel = ((Subsection) mainViewObj).getMainTableContainer().getTableModel().getTableData().get(clickedRow).get("Lecturer");
            DropdownButton contractType = ((ManageLecturerForm) form).getContractSelector();
            // Select the contract type before setting the other form fields, because the fields change depending on what contract the lecturer has.
            contractType.setSelectedItem(getSelectedObject(contractType, ((Lecturer) dataModel).getContract()));
        }
        else if (form instanceof ManageDepartmentForm)
        {
            dataModel = ((Subsection) mainViewObj).getMainTableContainer().getTableModel().getTableData().get(clickedRow).get("Department");
        }


        for (JPanel container : form.getInputsContainers())
        {
            Component[] inputElements = container.getComponents();

            for (Component element : inputElements)
            {
                formInputs.put(element.getName(), element);
            }
        }


        for (String key : formInputs.keySet())
        {
            if (key == null) continue;

            String text = "";

            Class<?> dataModelType = dataModel.getClass();
            Method method;

            try
            {
                method = dataModelType.getMethod("get" + key);
                text = (String) method.invoke(dataModel);
                if (text == null || text.isEmpty()) continue;
            }
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
            {
                continue;
            }

            if (formInputs.get(key) instanceof JTextComponent)
                ((JTextComponent) formInputs.get(key)).setText(text);

            if (formInputs.get(key) instanceof JComboBox) {
                DropdownItem selected = getSelectedObject(((JComboBox) formInputs.get(key)), text);
                ((JComboBox) formInputs.get(key)).setSelectedItem(selected);
            }
        }
    }

    private DropdownItem getSelectedObject(JComboBox selector, String selectedOption)
    {
        DropdownItem selected = null;
        System.out.println(selectedOption);

        for (int i = 0; i < selector.getItemCount(); i++)
        {
            if (((DropdownItem) selector.getItemAt(i)).getValue().equals(selectedOption))
            {
                selected = ((DropdownItem) selector.getItemAt(i));
                return selected;
            }
            else if (selectedOption.equals(null))
            {
                selected = ((DropdownItem) selector.getItemAt(0));
                return selected;
            }
        }

        return selected;
    }

    protected void saveObject(String buttonClicked)
    {
        visibleForm = ((Subsection) mainViewObj).getFormContainer();
        formContainer = ((Subsection) mainViewObj).getDialogBox();
        formData = (((Subsection) mainViewObj).getFormData(visibleForm.getInputsContainers()));
        DefaultTable tableContainer = ((Subsection) mainViewObj).getMainTableContainer();;
        HashMap<String, Object> row = new HashMap<>();
        String filterValue = "-1";
        JComboBox selector;
        DropdownItem selectedItem;

        // Form container is stored under a key with the same name of the button clicked
        switch (buttonClicked) {

            case "SaveLecturer":
                if (formData.size() == 0) break;

                Lecturer lecturer = new Lecturer();

                if (formData.containsKey("Contract"))
                {
                    switch (((String) formData.get("Contract")))
                    {
                        case "Full Time":
                            lecturer = new FullTimeLecturer();
                            break;
                        case "Part Time":
                            lecturer = new PartTimeLecturer();
                            break;
                        case "Contract":
                            lecturer = new ContractLecturer();
                            break;
                    }
                }

                lecturer.setFields(formData);

                lecturer.toFile("Lecturers\\", lecturer.getId(), lecturer);
                ((LecturersModel) mainDataModel).addLecturer(lecturer);

                row.put("Checkbox", false);
                row.put("Lecturer", lecturer);

                tableContainer.getTableModel().addRow(row);
                selector = (JComboBox) ((Subsection) mainViewObj).getFilterComponent("DepartmentType", DropdownButton.class);
                selectedItem = (selector != null) ? ((DropdownItem) selector.getSelectedItem()) : null;

                filterValue = (selectedItem != null) ? ((DropdownItem) selector.getSelectedItem()).getKey() : "-1";
                tableContainer.getTableController().refresh(tableContainer, "Contract", filterValue);
                break;

            case "SaveDepartment":
                if (formData.size() == 0) break;

                Department department = new Department();

                department.setFields(formData);

                department.toFile("Departments\\", department.getId(), department);
                ((DepartmentsModel) mainDataModel).addDepartment(department);

                row.put("Checkbox", false);
                row.put("Department", department);

                tableContainer.getTableModel().addRow(row);
                tableContainer.getTableController().refresh(tableContainer, "DepartmentType", "-1");
                break;

            default: break;
        }
    }
}
