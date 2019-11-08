package View.Section;

import Core.Controller;
import Core.Model;
import Models.Entities.Department;
import View.DefaultElement.DropdownButton;
import View.DefaultElement.DropdownItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class PopupWindow extends JPanel {

    protected Controller mainController;
    protected Model mainModel;
    protected JLabel jlTitle;

    public PopupWindow(Controller mainController, Model mainModel)
    {
        this.mainModel = mainModel;
        this.mainController = mainController;
    }

    protected void addToGrid(Container parent, Container container,
                             int column, int row,
                             double weightX, double weightY,
                             int spanColumns, int spanRows,
                             int fill, Integer align,
                             Insets insets, Integer padX, Integer padY)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = column;
        gbc.gridy = row;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.gridwidth = spanColumns;
        gbc.gridheight = spanRows;
        gbc.fill = fill;
        if (align != null)
            gbc.anchor = align;
        if (padX != null)
            gbc.ipadx = padX;
        if (padY != null)
            gbc.ipady = padY;
        if (insets != null)
            gbc.insets = insets;

        parent.add(container, gbc);
    }

    protected void addToGrid(Container parent, Component component,
                             int column, int row,
                             double weightX, double weightY,
                             int spanColumns, int spanRows,
                             int fill, Integer align,
                             Insets insets, Integer padX, Integer padY)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = column;
        gbc.gridy = row;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.gridwidth = spanColumns;
        gbc.gridheight = spanRows;
        gbc.fill = fill;
        if (align != null)
            gbc.anchor = align;
        if (padX != null)
            gbc.ipadx = padX;
        if (padY != null)
            gbc.ipady = padY;
        if (insets != null)
            gbc.insets = insets;

        parent.add(component, gbc);
    }

    public abstract HashMap<String, Object> getFormInputs();

    public HashMap<String, Object> getFormData(JPanel[] parentContainers)
    {

        HashMap<String, Object> formData = new HashMap<>();
        ArrayList<Component> formInputs = new ArrayList<>();

        // Add each input element from the parent containers to the formInputs list
        for (JPanel inputsContainer : parentContainers) {
            formInputs.addAll(Arrays.asList(inputsContainer.getComponents()));
        }

        // Generate the form data array as a HashMap with key -> value pairs
        // The key is the name set for each input element in the @createElements() function
        for (Component inputElement : formInputs) {
            if (inputElement instanceof JTextField || inputElement instanceof JFormattedTextField) {

                // Only add the input to the array if it is filled
                if (((JTextField) inputElement).getText() != null &&
                        ((JTextField) inputElement).getText().length() > 0)
                {
                    formData.put(inputElement.getName(), ((JTextField) inputElement).getText());
                }
            }
            else if (inputElement instanceof JComboBox) {

                if (((DropdownButton) inputElement).getSelectedIndex() != -1)
                {
                    formData.put(inputElement.getName(), ((DropdownItem) ((DropdownButton) inputElement).getSelectedItem()).getValue());
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
                selectorObj.addDropdownItem("PartTime", "Part Time", selectorObj);
                selectorObj.addDropdownItem("FullTime", "Full Time", selectorObj);
                selectorObj.addDropdownItem("Contract", "Contract", selectorObj);
                break;
            case "Department": {
                HashMap<String, Department> departmentsList = (this.mainController.getMainDataModel()).getDepartmentsList();

                selectorObj.addDropdownItem("-1", "DEPARTMENT", selectorObj);
                for (String key : departmentsList.keySet()) {
                    selectorObj.addDropdownItem(
                            departmentsList.get(key).getDepartmentName(),
                            departmentsList.get(key).getDepartmentName(),
                            selectorObj);
                }
                break;
            }
            case "DepartmentType": {
                HashMap<String, Department> departmentsList = (this.mainController.getMainDataModel()).getDepartmentsList();
                ArrayList<String> departmentType = new ArrayList<>();

                selectorObj.addDropdownItem("-1", "DEP. TYPE", selectorObj);
                for (String key : departmentsList.keySet()) {
                    if (!departmentType.contains(departmentsList.get(key).getDepartmentType()))
                    {
                        selectorObj.addDropdownItem(
                                departmentsList.get(key).getDepartmentType(),
                                departmentsList.get(key).getDepartmentType(),
                                selectorObj);
                        departmentType.add(departmentsList.get(key).getDepartmentType());
                    }
                }
                break;
            }
        }
    }



    protected abstract void loadImages();
    protected abstract void createElements();
    protected abstract void styleElements();
    protected abstract void addElements();
    protected abstract void addListeners();

    public abstract JPanel[] getInputsContainers();

    public Controller getMainController() {
        return mainController;
    }
}
