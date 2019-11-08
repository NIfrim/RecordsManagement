package View.Section.Departments;

import Controllers.DepartmentsController;
import Controllers.DepartmentsFormController;
import Controllers.TableController;
import Core.Controller;
import Models.DepartmentsModel;
import Models.Table.DepartmentsTableModel;
import View.DefaultElement.DefaultButton;
import View.DefaultElement.DefaultLabel;
import View.DefaultElement.InputWPlaceholder;
import View.Section.Subsection;

import javax.swing.*;
import java.awt.*;

public class DepartmentsSearchSubsection extends Subsection {

    // BUTTONS:
    private DefaultButton jbRemove;
    private DefaultButton jbAdd;

    // LABELS:
    private DefaultLabel jlName;
    private DefaultLabel jlType;
    private DefaultLabel jlId;

    // INPUTS:
    private InputWPlaceholder jtfDepartmentType;
    private InputWPlaceholder jtfDepartmentName;
    private InputWPlaceholder jtfId;

    DepartmentsSearchSubsection(Controller mainController, DepartmentsModel mainModel) {

        super(mainController, mainModel);
        this.tableController = new TableController(mainModel);
        this.formController = new DepartmentsFormController(mainModel);

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
        tableData = this.tableController.getTableData(null, null);
        DepartmentsTableModel departmentsTableModel = new DepartmentsTableModel(tableData);
        mainTableContainer = new DepartmentsTable(tableController , mainController.getMainDataModel(), departmentsTableModel);
        tableController.addView(this);
        tableController.addViewContainers("ManageDepartment", jdManageForm);

        // FORMS
        jpManage = new ManageDepartmentForm(this.formController, ((DepartmentsModel) mainModel));
        formController.addView(this);

        // BUTTONS
        jbAdd       = new DefaultButton("Create Department", "ManageDepartment");
        jbRemove    = new DefaultButton("Remove Selected", "RemoveSelected");

        // LABELS
        jlName      = new DefaultLabel("NAME: ");
        jlType      = new DefaultLabel("TYPE:");
        jlId        = new DefaultLabel("ID:");

        // INPUTS
        jtfDepartmentName  = new InputWPlaceholder(20, "Name...");
        jtfDepartmentName.setName("DepartmentName");
        jtfDepartmentType = new InputWPlaceholder(20, "Type...");
        jtfDepartmentType.setName("DepartmentType");
        jtfId = new InputWPlaceholder(20, "Id...");
        jtfId.setName("Id");
    }

    @Override
    protected void styleElements() {

        jpFilter.setLayout(new GridBagLayout());

        jdManageForm.setLayout(new BorderLayout());
        jdManageForm.setPreferredSize(new Dimension(600, 450));
        jdManageForm.setResizable(false);
        jdManageForm.pack();
        jdManageForm.setLocationRelativeTo(null);

        jtfDepartmentType.setPreferredSize(new Dimension(100, 30));
        jtfDepartmentName.setPreferredSize(new Dimension(100, 30));

        jtfDepartmentType.setMinimumSize(new Dimension(100, 30));
        jtfDepartmentName.setMinimumSize(new Dimension(100, 30));
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
        addToGrid(jpFilter, jtfDepartmentName,
                1, 0,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 5, 0), null, null);

        addToGrid(jpFilter, jlType,
                0, 1,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(0, 0, 5, 0), null, null);
        addToGrid(jpFilter, jtfDepartmentType,
                1, 1,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 5, 0), null, null);

        addToGrid(jpFilter, jlId,
                0, 2,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(0, 0, 20, 0), null, null);
        addToGrid(jpFilter, jtfId,
                1, 2,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 20, 0), null, null);

        addToGrid(jpFilter, jbRemove,
                0, 3,
                0, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 10, 0), null, null);
        addToGrid(jpFilter, jbAdd,
                0, 4,
                0, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 0, 0, 0), null, null);

        addToGrid(jpFilter, Box.createVerticalGlue(),
                0, 5,
                0, 1,
                2, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);
    }


    public JButton getAddButton() { return this.jbAdd; }


    @Override
    protected void addListeners()
    {
        mainTableContainer.getTable().addMouseListener(tableController);

        jtfDepartmentName.addActionListener(mainController);
        jtfDepartmentName.addKeyListener(((DepartmentsController) mainController));

        jtfDepartmentType.addActionListener(mainController);
        jtfDepartmentType.addKeyListener(((DepartmentsController) mainController));

        jtfId.addActionListener(mainController);
        jtfId.addKeyListener(((DepartmentsController) mainController));

        jbAdd.addActionListener(mainController);
        jbRemove.addActionListener(mainController);
    }
}
