package View.Section.Departments;

import Core.Controller;
import Models.DepartmentsModel;
import View.DefaultElement.SubNavButton;
import View.Section.Section;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentsSection extends Section {

    // FILTER PANEL
    protected JPanel jpFilter = new JPanel();

    // SUBSECTION PANELS
    private DepartmentsSearchSubsection jpDepartmentsSearchSubsection;

    // SUBNAVIGATION BUTTONS
    private SubNavButton jbSearch;

    // OHTER
    protected ArrayList<HashMap<String, Object>> tableData;

    public DepartmentsSection(String title, Controller lecturerController, DepartmentsModel mainModel)
    {
        super(title, lecturerController, mainModel);

        loadImages();
        createElements();
        styleElements();
        addElements();
        addListeners();
    }

    @Override
    protected void createElements() {

        // SUBNAV BUTTONS
        jbSearch = new SubNavButton("Search");
        subnavController.addViewComponents("SearchBtn", jbSearch);

        // SUBSECTIONS
        jpDepartmentsSearchSubsection = new DepartmentsSearchSubsection(mainController, ((DepartmentsModel) mainModel));
        mainController.addViewContainers("SearchSubsection", jpDepartmentsSearchSubsection);
    }

    @Override
    protected void loadImages() {

    }

    @Override
    protected void styleElements() {

        jpFilter.setOpaque(false);
        setOpaque(false);

        jpFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        // Set default active button
        jbSearch.setBackground(Color.WHITE);
    }

    @Override
    protected void addElements() {

        jpSubNavigation.add(jbSearch);
        jpSubNavigation.add(Box.createHorizontalStrut(5));

        // Add the subsections to the cardlayout, second param is a name for the section
        // currently using the sub-nav buttons text
        jpSectionContainer.add(jpDepartmentsSearchSubsection, jbSearch.getText());
    }

    @Override
    protected void addListeners() {
        jbSearch.addActionListener(this.subnavController);
    }


    public DepartmentsSearchSubsection getDepartmentsSearchSubsection() {
        return jpDepartmentsSearchSubsection;
    }
}
