package View.Section.Departments;

import Core.Controller;
import Models.DepartmentsModel;
import View.DefaultElement.*;
import View.Section.PopupWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;

public class ManageDepartmentForm extends PopupWindow {

    // PANELS
    private JPanel jpDepartmentInfo;
    private JPanel jpAccessInfo;
    private JPanel jpButtons;

    // LABELS
    // Department Information Labels
    private SubHeaderLabel  jlDepartmentInfo;
    private DefaultLabel    jlType;
    private DefaultLabel    jlName;
    private DefaultLabel    jlId;
    private DefaultLabel    jlURL;

    // Access Information Labels
    private SubHeaderLabel  jlAccessInfo;
    private DefaultLabel    jlUsername;
    private DefaultLabel    jlPassword;

    // INPUT FIELDS
    // Department Information Inputs
    private InputWPlaceholder  jtfId;
    private InputWPlaceholder  jtfName;
    private InputWPlaceholder  jtfURL;
    private InputWPlaceholder  jtfType;

    private DropdownButton jcbType;

    // Work Information inputs
    private InputWPlaceholder jtfUsername;
    private InputWPlaceholder jtfPassword;

    // BUTTONS
    private DefaultButton jbAdd;
    private DefaultButton jbCancel;
    private DefaultButton jbNewType;

    ManageDepartmentForm(Controller mainController, DepartmentsModel mainModel)
    {
        super(mainController, mainModel);

        setLayout(new GridBagLayout());
        setOpaque(false);

        createElements();
        styleElements();
        addElements();
        addListeners();
    }

    @Override
    public HashMap<String, Object> getFormInputs()
    {
        HashMap<String, Object> formFields = new HashMap<>();

        Container[] containers = this.getInputsContainers();

        for (Container container : containers)
        {
            Component[] components = container.getComponents();

            for (Component component : components)
            {
                if (!(component instanceof JLabel))
                    formFields.put(component.getName(), component);
            }
        }

        return formFields;
    }

    @Override
    protected void loadImages()
    {

    }

    @Override
    protected void createElements()
    {
        // PANELS
        jpDepartmentInfo = new JPanel();
        jpAccessInfo     = new JPanel();
        jpButtons        = new JPanel();

        // LABELS
        // Department Info
        jlTitle             = new HeaderLabel("Manage Departments");
        jlDepartmentInfo    =  new SubHeaderLabel("Department Information");
        jlId                = new DefaultLabel("Generated Id:");
        jlName              = new DefaultLabel("Name:");
        jlType              = new DefaultLabel("Type:");
        jlURL               =  new DefaultLabel("Web Address:");

        // Access Info
        jlAccessInfo = new SubHeaderLabel("Access Information");
        jlUsername = new DefaultLabel("Username:");
        jlPassword = new DefaultLabel("Password:");

        // INPUT FIELDS
        // Department Info
        jtfId = new InputWPlaceholder(20, "Generated Id...");
        jtfId.setName("Id");
        jtfId.setEditable(false);

        jtfName = new InputWPlaceholder(20, "Department Name...");
        jtfName.setName("DepartmentName");

        jcbType = new DropdownButton();
        jcbType.setName("DepartmentType");
        jcbType.setPrototypeDisplayValue("DepartmentType");
        setDropdownItems(jcbType);

        jtfType = new InputWPlaceholder(20, "Department Type...");

        jtfURL = new InputWPlaceholder(20, "Web Address...");
        jtfURL.setName("WebAddress");

        // Access Info
        jtfUsername = new InputWPlaceholder(20, "Username...");
        jtfUsername.setName("Username");

        jtfPassword = new InputWPlaceholder(20, "Password...");
        jtfPassword.setName("Password");

        // BUTTONS
        jbAdd       = new DefaultButton("Submit", "SaveDepartment");
        jbCancel    = new DefaultButton("Cancel", "Cancel");
        jbNewType   = new DefaultButton("New", "NewType");
    }

    @Override
    protected void styleElements()
    {
        Border margin15 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        Border darkUnderline = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 20, 0),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY)
        );

        // Layout managers
        jpDepartmentInfo.setLayout(new GridBagLayout());
        jpAccessInfo.setLayout(new GridBagLayout());
        jpButtons.setLayout(new GridBagLayout());

        // Borders
        jlDepartmentInfo.setBorder(darkUnderline);
        jlAccessInfo.setBorder(darkUnderline);

        jpDepartmentInfo.setBorder(margin15);
        jpAccessInfo.setBorder(margin15);

        // Transparency
        setOpaque(false);
    }

    @Override
    protected void addElements()
    {
        // ---------  Form title ------------------
        addToGrid(this, jlTitle,
                0, 0,
                1, 0,
                2, 1,
                GridBagConstraints.NONE, GridBagConstraints.CENTER,
                null, null, null);

        // ---------  Department Info Elements ------------------
        addToGrid(jpDepartmentInfo, jlDepartmentInfo,
                0, 0,
                1, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpDepartmentInfo, jlId,
                0, 1,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(5, 5, 5, 5), 5, 5);
        addToGrid(jpDepartmentInfo, jtfId,
                1, 1,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(5, 5, 5, 5), 5, 5);

        addToGrid(jpDepartmentInfo, jlName,
                0, 2,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(5, 5, 5, 5), 5, 5);
        addToGrid(jpDepartmentInfo, jtfName,
                1, 2,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(5, 5, 5, 5), 5, 5);

        addToGrid(jpDepartmentInfo, jlType,
                0, 3,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(5, 5, 5, 5), 5, 5);
        addToGrid(jpDepartmentInfo, jcbType,
                1, 3,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(5, 5, 5, 5), 5, 5);

        addToGrid(jpDepartmentInfo, jbNewType,
                0, 4,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(5, 5, 5, 5), 5, 5);
        addToGrid(jpDepartmentInfo, jtfType,
                1, 4,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(5, 5, 5, 5), 5, 5);

        addToGrid(jpDepartmentInfo, jlURL,
                0, 5,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(5, 5, 5, 5), 5, 5);
        addToGrid(jpDepartmentInfo, jtfURL,
                1, 5,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                new Insets(5, 5, 5, 5), 5, 5);

        addToGrid(jpDepartmentInfo, Box.createVerticalGlue(),
                0, 6,
                1, 1,
                2, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);

        // ---------  Access Info Elements ------------------
        addToGrid(jpAccessInfo, jlAccessInfo,
                0, 0,
                1, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpAccessInfo, jlUsername,
                0, 1,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(5, 5, 5, 5), 5, 5);
        addToGrid(jpAccessInfo, jtfUsername,
                1, 1,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(5, 5, 5, 5), 5, 5);

        addToGrid(jpAccessInfo, jlPassword,
                0, 2,
                0, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                new Insets(5, 5, 5, 5), 5, 5);
        addToGrid(jpAccessInfo, jtfPassword,
                1, 2,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(5, 5, 5, 5), 5, 5);

        addToGrid(jpAccessInfo, Box.createVerticalGlue(),
                0, 4,
                1, 1,
                2, 1,
                GridBagConstraints.BOTH, null,
                new Insets(5, 5, 5, 5), null, null);

        // ----------------  Form Buttons ---------------------------------
        addToGrid(jpButtons, jbAdd,
                0, 0,
                0.1, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.WEST,
                null, 5, 5);

        addToGrid(jpButtons, jbCancel,
                1, 0,
                0.1, 0,
                1, 1,
                GridBagConstraints.NONE, GridBagConstraints.EAST,
                null, 5, 5);

        // ----------------  Add Form Container Panels ------------------
        addToGrid(this, jpDepartmentInfo,
                0, 1,
                1, 0.5,
                1, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);

        addToGrid(this, jpAccessInfo,
                1, 1,
                1, 1,
                1, 2,
                GridBagConstraints.BOTH, null,
                null, null, null);

        addToGrid(this, jpButtons,
                0, 3,
                1, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 10, 15, 10), null, null);
    }

    public void generateId()
    {
        String lastId = "1";

        // Each lecturer is stored under a key, with the key being the lecturer_id
        for (String key : ((DepartmentsModel) mainModel).getDepartmentsList().keySet())
        {
            if (Integer.valueOf(lastId) <= Integer.valueOf(key))
            {
                int id = Integer.parseInt(key) + 1;
                lastId = String.valueOf(id);
            }
        }

        jtfId.setText(lastId);

        jtfId.revalidate();
        jtfId.repaint();
    }

    public DropdownButton getTypeSelector() {
        return jcbType;
    }

    public InputWPlaceholder getIdInputField() {
        return jtfId;
    }

    public InputWPlaceholder getTypeInputField() { return jtfType; }

    @Override
    public JPanel[] getInputsContainers() {
        return new JPanel[]{jpDepartmentInfo, jpAccessInfo};
    }

    @Override
    protected void addListeners()
    {
        jbAdd.addActionListener(this.mainController);
        jbCancel.addActionListener(this.mainController);
        jbNewType.addActionListener(this.mainController);
        jcbType.addActionListener(this.mainController);
    }
}
