package Core;

import Models.Entities.Department;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JPanel {

    protected Department departmentLoggedIn;
    protected Controller mainController;
    protected String title;

    public View() {
        this("Missing Title");
    }

    public View(String title) {
        this(title, null);
    }

    public View(String title, Controller mainController) {

        this.title = title;
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

    protected void setMainController(Controller mainController) { this.mainController = mainController; }
    protected abstract void loadImages();
    protected abstract void createElements();
    protected abstract void styleElements();
    protected abstract void addElements();
    protected abstract void addListeners();


    public Controller getMainController() {
        return mainController;
    }
}
