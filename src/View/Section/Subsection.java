package View.Section;

import Controllers.TableController;
import Core.Controller;
import Core.Model;
import Models.Entities.Department;
import View.DefaultElement.DefaultTable;
import View.DefaultElement.DropdownButton;
import View.DefaultElement.DropdownItem;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Subsection extends Section {

    protected JPanel jpFilter = new JPanel();

    private int pixels;

    // FORMS WITH DIALOG BOX
    protected PopupWindow jpManage;
    protected JDialog     jdManageForm;

    // TABLE:
    protected DefaultTable mainTableContainer;

    // OHTER
    protected ArrayList<HashMap<String, Object>> tableData;
    protected TableController tableController;
    protected Model mainModel;

    // DefaultElement constructor
    public Subsection() {
        super();
    }

    public Subsection(Controller mainController, Model mainModel) {
        super(mainController);
        this.mainModel = mainModel;
        setDefaultStyle();
    }

    private void setDefaultStyle() {

        setLayout(new GridBagLayout());

        jpFilter.setOpaque(false);

        jpFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        jpFilter.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
    }

    public HashMap<String, Object> getFormData(JPanel[] parentContainers) {

        HashMap<String, Object> formData = new HashMap<>();
        LinkedList<Component> inputContainers = new LinkedList<>();

        // Add each input element from the parent containers to the formInputs list
        for (JPanel inputsContainer : parentContainers) {
            inputContainers.addAll(Arrays.asList(inputsContainer.getComponents()));
        }

        // Generate the form data array as a HashMap with key -> value pairs
        // The key is the name set for each input element in the @createElements() function
        for (Component inputElement : inputContainers)
        {
            if (inputElement instanceof JTextComponent)
            {
                // Only add the input to the array if it is filled
                if (((JTextComponent)inputElement).getText() != null &&
                        ((JTextComponent)inputElement).getText().length() > 0)
                {
                    formData.put(
                            inputElement.getName(),
                            ((JTextComponent) inputElement).getText()
                    );
                }
            }
            else if (inputElement instanceof JComboBox) {

                System.out.println(((JComboBox) inputElement).getSelectedIndex());
                if (((JComboBox) inputElement).getSelectedIndex() != 0)
                {
                    if ((!((DropdownItem) ((JComboBox) inputElement).getSelectedItem()).getKey().equals("-1")))
                    {
                        formData.put(
                                String.valueOf(((DropdownButton)inputElement).getName()),
                                String.valueOf(((DropdownItem)((DropdownButton)inputElement).getSelectedItem()).getValue())
                        );
                    }
                }
            }
        }

        return formData;
    }


    protected void setDropdownItems(DropdownButton selectorObj)
    {
        switch (selectorObj.getName())
        {
            case "Contract":
                selectorObj.addDropdownItem("-1", "CONTRACT TYPE", selectorObj);
                selectorObj.addDropdownItem("FullTime", "Full Time", selectorObj);
                selectorObj.addDropdownItem("PartTime", "Part Time", selectorObj);
                selectorObj.addDropdownItem("Contract", "Contract",selectorObj);
                break;
            case "DepartmentType": {
                HashMap<String, Department> departmentsList = this.mainController.getMainDataModel().getDepartmentsList();
                String departmentType = "";

                selectorObj.addDropdownItem("-1","DEP. TYPE", selectorObj);

                for (String key : departmentsList.keySet()) {
                    if (!departmentsList.get(key).getDepartmentType().equals(departmentType))
                    {
                        selectorObj.addDropdownItem(
                                departmentsList.get(key).getDepartmentType(),
                                departmentsList.get(key).getDepartmentType(),
                                selectorObj);
                        departmentType = departmentsList.get(key).getDepartmentType();
                    }
                }
                break;
            }
        }
    }


    public Department getDepartmentLoggedIn()
    {
        return this.departmentLoggedIn;
    }

    public DefaultTable getMainTableContainer() {
        return mainTableContainer;
    }

    public TableController getTableController() {
        return tableController;
    }

    public PopupWindow getFormContainer() {
        return jpManage;
    }

    public JDialog getDialogBox() {
        return jdManageForm;
    }

    public JPanel getFilterPanel() {
        return jpFilter;
    }

    public Component getFilterComponent(String name, Class<?> type)
    {
        Component[] components = jpFilter.getComponents();

        Component component = null;

        for (Component element : components)
        {
            if (name.equals(element.getName()) && element.getClass().equals(type))
                component = element;
        }

        return component;
    }

    public abstract JButton getAddButton();

    @Override
    protected abstract void loadImages();

    @Override
    protected abstract void createElements();

    @Override
    protected abstract void styleElements();

    @Override
    protected abstract void addElements();

    @Override
    protected abstract void addListeners();
}
