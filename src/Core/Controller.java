package Core;

import View.Section.PopupWindow;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Controller implements ActionListener {

    protected HashMap<String, Container> viewContainers;
    protected HashMap<String, Component> viewComponents;
    protected HashMap<String, Model> modelObjects;
    protected Container mainViewObj;
    protected Model mainDataModel;

    public Controller() {
        this(null);
    }

    public Controller(Model dataModel) {
        this.mainDataModel = dataModel;
        this.viewContainers = new HashMap<String, Container>();
        this.viewComponents = new HashMap<String, Component>();
        this.modelObjects = new HashMap<String, Model>();
    }

    public void addView(Container mainViewObject){
        this.mainViewObj = mainViewObject;
    }

    public void addModel(Model mainDataModel){
        this.mainDataModel = mainDataModel;
    }

    /**
     * Adds the interface element to an array of elements, as a key -> value pair
     * @param key: The name of the key at which the viewObject is stored
     * @param viewObject: The interface element (JPanel, JComponent, ...)
     * */
    public void addViewContainers(String key, Container viewObject){
        this.viewContainers.put(key, viewObject);
    }

    public void addViewComponents(String key, Component viewObject){
        this.viewComponents.put(key, viewObject);
    }

    /**
     * Adds the data object used to an array of elements, as a key -> value pair
     * @param key: The name of the key at which the modelObject is stored
     * @param modelObject: Entities storing data (Student, Lecturer, DatabaseTable connection, ...)
     * */
    public void addModels(String key, Model modelObject){
        this.modelObjects.put(key, modelObject);
    }

    /**
     * Function to return number of files in a directory
     *
     * @param path: Path of the directory
     * */
    protected ArrayList<File> getFiles(String path) {

        File f = new File(path);
        ArrayList<File> filesList;

        filesList = new ArrayList<>(Arrays.asList(f.listFiles((dir, name) -> name.endsWith("ser"))));

        return filesList;
    }


    /**
     * Function uses the button name to get the form, make it visible, and add a generated id.
     *
     * @param formName: Key of the form object inside viewContainers HashMap.
     * */
    protected void showForm(String formName)
    {
        Container visibleForm = viewContainers.get(formName);
        JPanel formContainer = ((JPanel) ((JDialog) visibleForm).getContentPane().getComponent(0));

        emptyFormFields(formContainer);
        visibleForm.setVisible(true);
    }

    private void emptyFormFields(JPanel formContainer)
    {
        JPanel[] inputsContainers = ((PopupWindow) formContainer).getInputsContainers();

        for (JPanel container : inputsContainers)
        {
            for (Component input : container.getComponents()) {
                if (input instanceof JTextComponent)
                {
                    if (input.getName() != null && !input.getName().equals("Id"))
                        ((JTextComponent) input).setText("");
                }
                else if (input instanceof JComboBox)
                {
                    ((JComboBox) input).setSelectedIndex(0);
                }
            }
        }
    }

    public HashMap<String, Model> getModelObjects() {
        return modelObjects;
    }

    public Model getMainDataModel()
    {
        return mainDataModel;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public abstract void actionPerformed(ActionEvent e);
}
