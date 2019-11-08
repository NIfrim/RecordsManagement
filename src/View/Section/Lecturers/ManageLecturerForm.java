package View.Section.Lecturers;

import Core.Controller;
import Models.LecturersModel;
import View.DefaultElement.*;
import View.Section.PopupWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;

public class ManageLecturerForm extends PopupWindow {

    // PANELS
    private JPanel jpPersonalInfo;
    private JPanel jpWorkInfo;
    private JPanel jpContactInfo;
    private JPanel jpButtons;

    // LABELS
    // Personal information labels
    private SubHeaderLabel  jlPersonalInfoTitle;
    private DefaultLabel    jlId;
    private DefaultLabel    jlFullName;

    // Work information labels
    private SubHeaderLabel jlWorkInfoTitle;
    private DefaultLabel   jlDepartment;
    private DefaultLabel   jlContract;
    private DefaultLabel   jlSalary;
    private DefaultLabel   jlHourlyPay;
    private DefaultLabel   jlStartDate;
    private DefaultLabel   jlEndDate;

    // Contact information labels
    private SubHeaderLabel jlContactInfoTitle;
    private DefaultLabel   jlCity;
    private DefaultLabel   jlStreet;
    private DefaultLabel   jlPostcode;
    private DefaultLabel   jlEmail;
    private DefaultLabel   jlMobile;

    // INPUT FIELDS
    // Personal Information inputs
    private InputWPlaceholder  jtfId;
    private InputWPlaceholder  jtfFullName;

    // Work Information inputs
    private DropdownButton      jcbDepartment;
    private DropdownButton      jcbContract;
    private InputWPlaceholder   jtfSalary;
    private InputWPlaceholder   jtfHourlyPay;
    private DateInput           diStartDate;
    private DateInput           diEndDate;

    // Contact Information inputs
    private InputWPlaceholder   jtfCity;
    private InputWPlaceholder   jtfStreet;
    private InputWPlaceholder   jtfPostcode;
    private InputWPlaceholder   jtfEmail;
    private InputWPlaceholder   jtfMobile;

    // BUTTONS
    private DefaultButton jbAdd;
    private DefaultButton jbCancel;

    ManageLecturerForm(Controller mainController, LecturersModel mainModel)
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
        jpPersonalInfo   = new JPanel();
        jpWorkInfo       = new JPanel();
        jpContactInfo    = new JPanel();
        jpButtons        = new JPanel();

        // LABELS
        // Personal Information
        jlId                = new DefaultLabel("Generated Id:");
        jlTitle             = new HeaderLabel("Manage Lecturers");
        jlPersonalInfoTitle =  new SubHeaderLabel("Personal Information");
        jlFullName         =  new DefaultLabel("Full Name:");

        // Work Information
        jlWorkInfoTitle     = new SubHeaderLabel("Work Information");
        jlDepartment        = new DefaultLabel("Department:");
        jlContract          = new DefaultLabel("Contract:");
        jlSalary            = new DefaultLabel("Salary:");
        jlHourlyPay         = new DefaultLabel("Hourly Pay:");
        jlStartDate         = new DefaultLabel("Start Date:");
        jlEndDate           = new DefaultLabel("End Date:");

        // Contact Information
        jlContactInfoTitle   = new SubHeaderLabel("Contact Information");
        jlCity               = new DefaultLabel("City:");
        jlStreet             = new DefaultLabel("Street:");
        jlPostcode           = new DefaultLabel("Postcode:");
        jlEmail              = new DefaultLabel("Email:");
        jlMobile             = new DefaultLabel("Mobile:");

        // INPUT FIELDS
        // Personal Info inputs
        jtfId           = new InputWPlaceholder(20, "Generated Id...");
        jtfId.setName("Id");
        jtfId.setEditable(false);

        jtfFullName = new InputWPlaceholder(20, "First Name...");
        jtfFullName.setName("FullName");

        // Work Info inputs
        jcbDepartment   = new DropdownButton();
        jcbDepartment.setName("Department");
        jcbDepartment.setPrototypeDisplayValue("Department");
        setDropdownItems(jcbDepartment);

        jcbContract     = new DropdownButton();
        jcbContract.setName("Contract");
        jcbContract.setPrototypeDisplayValue("Contract");
        setDropdownItems(jcbContract);

        jtfSalary       = new InputWPlaceholder(20, "Salary...");
        jtfSalary.setName("Salary");

        jtfHourlyPay    = new InputWPlaceholder(20, "Hourly pay...");
        jtfHourlyPay.setName("HourlyRate");

        diStartDate     = new DateInput("YYYY-MM-DD");
        diStartDate.setName("StartDate");

        diEndDate       = new DateInput("YYYY-MM-DD");
        diEndDate.setName("EndDate");

        // Contact Info inputs
        jtfCity     = new InputWPlaceholder(20, "City...");
        jtfCity.setName("City");

        jtfStreet   = new InputWPlaceholder(20, "Street...");
        jtfStreet.setName("Address");

        jtfPostcode = new InputWPlaceholder(20, "Postcode...");
        jtfPostcode.setName("Postcode");

        jtfEmail    = new InputWPlaceholder(20, "Email...");
        jtfEmail.setName("Email");

        jtfMobile   = new InputWPlaceholder(20, "Mobile...");
        jtfMobile.setName("ContactNumber");

        // BUTTONS
        jbAdd    = new DefaultButton("Submit", "SaveLecturer");
        jbCancel = new DefaultButton("Cancel", "Cancel");
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
        jpPersonalInfo.setLayout(new GridBagLayout());
        jpWorkInfo.setLayout(new GridBagLayout());
        jpContactInfo.setLayout(new GridBagLayout());
        jpButtons.setLayout(new GridBagLayout());

        // Borders
        jlPersonalInfoTitle.setBorder(darkUnderline);
        jlContactInfoTitle.setBorder(darkUnderline);
        jlWorkInfoTitle.setBorder(darkUnderline);

        jpPersonalInfo.setBorder(margin15);
        jpContactInfo.setBorder(margin15);
        jpWorkInfo.setBorder(margin15);

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

        // ---------  Personal Info Elements ------------------
        addToGrid(jpPersonalInfo, jlPersonalInfoTitle,
                0, 0,
                1, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpPersonalInfo, jlId,
                0, 1,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
        addToGrid(jpPersonalInfo, jtfId,
                1, 1,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpPersonalInfo, jlFullName,
                0, 2,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
        addToGrid(jpPersonalInfo, jtfFullName,
                1, 2,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        // ---------  Contact Info Elements ------------------
        addToGrid(jpContactInfo, jlContactInfoTitle,
                0, 0,
                1, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jlCity,
                0, 1,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jtfCity,
                1, 1,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jlStreet,
                0, 2,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jtfStreet,
                1, 2,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jlPostcode,
                0, 3,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jtfPostcode,
                1, 3,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jlEmail,
                0, 4,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jtfEmail,
                1, 4,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jlMobile,
                0, 5,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpContactInfo, jtfMobile,
                1, 5,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        // ---------  Work Info Elements ------------------
        addToGrid(jpWorkInfo, jlWorkInfoTitle,
                0, 0,
                1, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jlDepartment,
                0, 1,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
        addToGrid(jpWorkInfo, jcbDepartment,
                1, 1,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jlContract,
                0, 2,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
        addToGrid(jpWorkInfo, jcbContract,
                1, 2,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addDefaultInputs();

        addToGrid(jpWorkInfo, Box.createVerticalGlue(),
                1, 6,
                1, 1,
                1, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);

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
        addToGrid(this, jpPersonalInfo,
                0, 1,
                1, 0.5,
                1, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);

        addToGrid(this, jpContactInfo,
                0, 2,
                1, 1,
                1, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);

        addToGrid(this, jpWorkInfo,
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

    public void addPartTimeLecturerInputs()
    {
        removeComponents();

        addToGrid(jpWorkInfo, jlHourlyPay,
                0, 3,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jtfHourlyPay,
                1, 3,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jlStartDate,
                0, 4,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, diStartDate,
                1, 4,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
    }

    public void addFullTimeLecturerInputs()
    {
        removeComponents();

        addToGrid(jpWorkInfo, jlSalary,
                0, 3,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jtfSalary,
                1, 3,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jlStartDate,
                0, 4,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, diStartDate,
                1, 4,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
    }

    public void addContractLecturerInputs()
    {
        removeComponents();

        addToGrid(jpWorkInfo, jlSalary,
                0, 3,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jtfSalary,
                1, 3,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jlStartDate,
                0, 4,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, diStartDate,
                1, 4,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, jlEndDate,
                0, 5,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, diEndDate,
                1, 5,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
    }

    public void addDefaultInputs()
    {
        removeComponents();

        addToGrid(jpWorkInfo, jlStartDate,
                0, 3,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);

        addToGrid(jpWorkInfo, diStartDate,
                1, 3,
                1, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH,
                null, 5, 5);
    }

    private void removeComponents()
    {
        Component[] components = jpWorkInfo.getComponents();

        for (int i = 0; i < components.length; i++)
        {
            if (components[i] == jlHourlyPay || components[i] == jlSalary ||
                    components[i] == jlStartDate || components[i] == jlEndDate)
                jpWorkInfo.remove(components[i]);

            if (components[i] == jtfHourlyPay || components[i] == jtfSalary ||
                    components[i] == diStartDate || components[i] == diEndDate)
                jpWorkInfo.remove(components[i]);
        }
    }

    public void generateId()
    {
        String lastId = "1";

        // Each lecturer is stored under a key, with the key being the lecturer_id
        for (String key : mainModel.getLecturersList().keySet())
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

    @Override
    protected void addListeners()
    {
        jbAdd.addActionListener(this.mainController);
        jbCancel.addActionListener(this.mainController);
        jcbContract.addActionListener(this.mainController);
    }

    public DropdownButton getContractSelector() {
        return jcbContract;
    }

    public InputWPlaceholder getIdInputField() {
        return jtfId;
    }

    @Override
    public JPanel[] getInputsContainers() {
        return new JPanel[]{jpContactInfo, jpPersonalInfo, jpWorkInfo};
    }
}
