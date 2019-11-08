package View;

import Controllers.DepartmentsController;
import Controllers.LecturersController;
import Core.Controller;
import Core.View;
import Models.DepartmentsModel;
import Models.Entities.Department;
import Models.LecturersModel;
import View.Section.Departments.DepartmentsSection;
import View.Section.Lecturers.LecturersSection;

import javax.swing.*;
import java.awt.*;

public class SectionsContainer extends View {

    // Section Panels
    private LecturersSection jpLecturers;
    private DepartmentsSection jpDepartments;

    // Others
    private CardLayout clMain;
    private LecturersController   lecturersController;
    private DepartmentsController departmentsController;

    public SectionsContainer(Controller mainController, Department departmentLoggedIn) {

        super(null, mainController);
        clMain = new CardLayout();

        this.departmentLoggedIn = departmentLoggedIn;

        createElements();
        styleElements();

        addElements();
        addListeners();
    }

    @Override
    protected void createElements() {

        LecturersModel lecturersModel = new LecturersModel();
        lecturersController = new LecturersController(lecturersModel);
        jpLecturers = new LecturersSection("Manage Lecturers", lecturersController, lecturersModel, departmentLoggedIn);
        lecturersController.addView(jpLecturers);

        DepartmentsModel departmentsModel = new DepartmentsModel();
        departmentsController = new DepartmentsController(departmentsModel);
        jpDepartments = new DepartmentsSection("Manage Departments", departmentsController, departmentsModel);
        departmentsController.addView(jpDepartments);
    }

    private void setMaxSize(Component[] components, int width, int height) {

        for (Component component:components) {

            if (component instanceof JComboBox) {

                component.setMaximumSize(new Dimension(width, height));
            }
        }
    }

    @Override
    protected void loadImages(){}

    @Override
    protected void styleElements()
    {
        setLayout(clMain);
        setOpaque(false);
    }

    @Override
    protected void addElements()
    {
        add(jpLecturers, "Lecturers ");
        add(jpDepartments, "Departments ");

        // DefaultElement section on application start
        clMain.show(this, "Lecturers ");
    }

    @Override
    protected void addListeners() {

    }
}

