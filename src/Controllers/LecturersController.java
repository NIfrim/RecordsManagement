package Controllers;

import Core.Controller;
import Core.Model;
import Models.Entities.Lecturer;
import View.DefaultElement.DefaultButton;
import View.DefaultElement.DefaultTable;
import View.DefaultElement.DropdownItem;
import View.Section.Lecturers.LecturersSection;
import View.Section.Lecturers.ManageLecturerForm;
import View.Section.PopupWindow;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class LecturersController extends Controller implements KeyListener {

    public LecturersController(Model dataModel) {
        super(dataModel);
    }

    protected void deleteSelected()
    {
        DefaultTable tableContainer = ((LecturersSection) mainViewObj).getLecturerSearchSubsection().getMainTableContainer();

        ArrayList<HashMap<String, Object>> selectedRows = tableContainer.getTableController().getSelectedRows(tableContainer);
        ArrayList<String> lecturersIds = new ArrayList<>();

        // Remove the objects from the array with data models
        for (HashMap<String, Object> row : selectedRows)
        {
            lecturersIds.add(((Lecturer) row.get("Lecturer")).getId());
        }

        if (lecturersIds.size() != 0)
        {
            tableContainer.getTableController().removeSelectedRows(lecturersIds);
            mainDataModel.removeRelatedFiles("src\\Files\\Lecturers\\", lecturersIds);
            tableContainer.getTableController().refresh(tableContainer, "Contract", "-1");
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
        DefaultTable tableContainer = (((LecturersSection) mainViewObj).getLecturerSearchSubsection().getMainTableContainer());

        if (source instanceof JButton)
        {
            if (((DefaultButton) source).getName().equals("ManageLecturer"))
            {
                PopupWindow formContainer = ((LecturersSection) mainViewObj).getLecturerSearchSubsection().getFormContainer();
                ((ManageLecturerForm) formContainer).generateId();

                showForm("ManageLecturer");
            }
            else if (((DefaultButton) source).getName().equals("RemoveSelected"))
            {
                deleteSelected();
            }
        }
        else if (source instanceof JComboBox)
        {
            if (((JComboBox) source).getSelectedIndex() == 0)
            {
                tableContainer.getTableController().refresh(tableContainer, ((JComboBox) source).getName(), "-1");
            }
            else {
                tableContainer.getTableController().refresh(tableContainer, ((JComboBox) source).getName(), ((DropdownItem) ((JComboBox) source).getSelectedItem()).getValue());
            }
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

        DefaultTable tableContainer = (((LecturersSection) mainViewObj).getLecturerSearchSubsection().getMainTableContainer());

        if (source instanceof JTextComponent)
        {
            String text = ((JTextComponent) source).getText().equals("") ? null : ((JTextComponent) source).getText();
            tableContainer.getTableController().refresh(tableContainer, ((JTextComponent) source).getName(), text);
        }
    }
}
