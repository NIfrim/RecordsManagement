package View.Section.Lecturers;

import Controllers.LecturersController;
import Controllers.LecturersFormController;
import Controllers.TableController;
import Core.Controller;
import Models.Entities.Department;
import Models.LecturersModel;
import Models.Table.LecturersTableModel;
import View.DefaultElement.DefaultButton;
import View.DefaultElement.DefaultLabel;
import View.DefaultElement.DropdownButton;
import View.DefaultElement.InputWPlaceholder;
import View.Section.Subsection;

import javax.swing.*;
import java.awt.*;

public class LecturerSearchSubsection extends Subsection {

    // BUTTONS:
    private DefaultButton jbRemove;
    private DefaultButton jbAdd;

    // LABELS:
    private DefaultLabel jlName;
    private DefaultLabel jlId;
    private DefaultLabel jlEmail;
    private DefaultLabel jlDepartment;

    // INPUTS:
    private DropdownButton jcbContractType;

    private InputWPlaceholder jtfName;
    private InputWPlaceholder jtfId;
    private InputWPlaceholder jtfEmail;
    private InputWPlaceholder jtfDepartment;

    LecturerSearchSubsection(Controller mainController, LecturersModel mainModel, Department departmentLoggedIn) {

        super(mainController, mainModel);
        this.departmentLoggedIn = departmentLoggedIn;
        this.tableController = new TableController(mainModel);
        this.formController = new LecturersFormController(mainModel);

        loadImages();
        createElements();
        styleElements();
        addElements();
        addListeners();
    }

    @Override
    protected void loadImages() {

    }

    @Override
    protected void createElements() {

        // POPUP DIALOG WITH PARENT OWNER
        Window parentWindow = SwingUtilities.windowForComponent(this);
        jdManageForm = new JDialog(parentWindow, "Event Form");

        // TABLE
        tableController.addModels("Department", this.departmentLoggedIn);
        tableData = this.tableController.getTableData(null, null);
        LecturersTableModel lecturersTableModel = new LecturersTableModel(tableData);
        mainTableContainer = new LecturersTable(tableController , mainController.getMainDataModel(), lecturersTableModel);
        tableController.addView(this);
        tableController.addViewContainers("ManageLecturer", jdManageForm);


        // FORMS
        jpManage = new ManageLecturerForm(this.formController, ((LecturersModel) mainModel));
        formController.addView(this);

        // BUTTONS
        jbAdd       = new DefaultButton("Create Lecturer", "ManageLecturer");
        jbRemove    = new DefaultButton("Remove Selected", "RemoveSelected");

        // LABELS
        jlName          = new DefaultLabel("NAME: ");
        jlId            = new DefaultLabel("ID: ");
        jlEmail         = new DefaultLabel("EMAIL: ");
        jlDepartment    = new DefaultLabel("DEPARTMENT:");

        // INPUTS
        jtfName  = new InputWPlaceholder(20, "Name...");
        jtfName.setName("FullName");
        jtfId    = new InputWPlaceholder(20, "Lecturer Id...");
        jtfId.setName("Id");
        jtfEmail = new InputWPlaceholder(20, "Email...");
        jtfEmail.setName("Email");
        jtfDepartment = new InputWPlaceholder(20, "Department...");
        jtfDepartment.setName("Department");


        // DROP DOWN INPUTS
        jcbContractType = new DropdownButton();
        jcbContractType.setName("Contract");
        setDropdownItems(jcbContractType);
    }

    @Override
    protected void styleElements() {

        if (!this.departmentLoggedIn.getDepartmentType().equals("Administration"))
        {
            jbRemove.setEnabled(false);
            jbAdd.setEnabled(false);
        }

        jpFilter.setLayout(new GridBagLayout());

        jdManageForm.setLayout(new BorderLayout());
        jdManageForm.setPreferredSize(new Dimension(600, 600));
        jdManageForm.setResizable(false);
        jdManageForm.pack();
        jdManageForm.setLocationRelativeTo(null);
    }

    @Override
    protected void addElements() {

    // ADD FORM TO DIALOG BOX
        jdManageForm.add(jpManage, BorderLayout.CENTER);
        mainController.addViewContainers(jbAdd.getName(), jdManageForm);

    // FILTER WITH ELEMENTS:
        // FILTER PANEL
        addToGrid(this, jpFilter,
                0, 0,
                0, 1,
                1, 1,
                GridBagConstraints.BOTH, null,
                new Insets(15, 15, 15, 15), 0, 0);

        // TABLE
        addToGrid(this, mainTableContainer,
                1, 0,
                1, 1,
                1, 1,
                GridBagConstraints.BOTH, null,
                new Insets(15, 15, 15, 15), null, null);

        // ELEMENTS:
        // Filter elements:
        addToGrid(jpFilter, jlName,
                0, 0,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(0, 0, 5, 0), null, null);
        addToGrid(jpFilter, jtfName,
                1, 0,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 5, 0), null, null);

        addToGrid(jpFilter, jlId,
                0, 1,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(0, 0, 5, 0), null, null);
        addToGrid(jpFilter, jtfId,
                1, 1,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 5, 0), null, null);

        addToGrid(jpFilter, jlEmail,
                0, 2,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(0, 0, 5, 0), null, null);
        addToGrid(jpFilter, jtfEmail,
                1, 2,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 5, 0), null, null);

        addToGrid(jpFilter, jlDepartment,
                0, 3,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(0, 0, 5, 0), null, null);
        addToGrid(jpFilter, jtfDepartment,
                1, 3,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 5, 0), null, null);

        addToGrid(jpFilter, jcbContractType,
                0, 4,
                1, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(15, 0, 20, 0), null, null);

        addToGrid(jpFilter, jbRemove,
                0, 5,
                0, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 10, 0), null, null);

        addToGrid(jpFilter, jbAdd,
                0, 6,
                0, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 0, 0), null, null);

        addToGrid(jpFilter, Box.createVerticalGlue(),
                0, 7,
                1, 1,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                null, null, null);
    }


    public JButton getAddButton() { return this.jbAdd; }




    @Override
    protected void addListeners()
    {
        if (departmentLoggedIn.getDepartmentType().equals("Administration"))
            mainTableContainer.getTable().addMouseListener(tableController);

        jtfName.addActionListener(mainController);
        jtfName.addKeyListener(((LecturersController) mainController));

        jtfId.addActionListener(mainController);
        jtfId.addKeyListener(((LecturersController) mainController));

        jtfEmail.addActionListener(mainController);
        jtfEmail.addKeyListener(((LecturersController) mainController));

        jtfDepartment.addActionListener(mainController);
        jtfDepartment.addKeyListener(((LecturersController) mainController));

        jcbContractType.addActionListener(mainController);
        jbAdd.addActionListener(mainController);
        jbRemove.addActionListener(mainController);
    }
}
