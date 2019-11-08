package View.Section.Lecturers;

import Core.Controller;
import Models.Entities.Department;
import Models.LecturersModel;
import View.DefaultElement.SubNavButton;
import View.Section.Section;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LecturersSection extends Section {

    // FILTER PANEL
    protected JPanel jpFilter = new JPanel();

    // SUBSECTION PANELS
    private LecturerSearchSubsection jpLecturerSearchSubsection;

    // SUBNAVIGATION BUTTONS
    private SubNavButton jbSearch;

    // OHTER
    protected ArrayList<HashMap<String, Object>> tableData;

    public LecturersSection(String title, Controller lecturerController, LecturersModel mainModel, Department departmentLoggedIn)
    {
        super(title, lecturerController, mainModel);
        this.departmentLoggedIn = departmentLoggedIn;

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
        jpLecturerSearchSubsection = new LecturerSearchSubsection(mainController, ((LecturersModel) mainModel), departmentLoggedIn);
        mainController.addViewContainers("SearchSubsection", jpLecturerSearchSubsection);
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
        jpSectionContainer.add(jpLecturerSearchSubsection, jbSearch.getText());
    }

    @Override
    protected void addListeners() {
        jbSearch.addActionListener(this.subnavController);
    }


    public LecturerSearchSubsection getLecturerSearchSubsection() {
        return jpLecturerSearchSubsection;
    }
}
