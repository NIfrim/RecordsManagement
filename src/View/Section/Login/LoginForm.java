package View.Section.Login;

import Core.Controller;
import Core.View;
import Models.DepartmentsModel;
import View.DefaultElement.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoginForm extends View {

    // PANELS
    private JPanel jpAccessInfo;
    private JPanel jpButtons;

    // LABELS
    // Access Information Labels
    private SubHeaderLabel  jlAccessInfo;
    private DefaultLabel    jlUsername;
    private DefaultLabel    jlPassword;

    // INPUT FIELDS
    // Work Information inputs
    private InputWPlaceholder jtfUsername;
    private PasswordInputWPlaceholder jpfPassword;

    // OTHER
    private Controller mainController;
    private DepartmentsModel mainModel;

    // BUTTONS
    private DefaultButton jbLogin;

    LoginForm(String title, Controller mainController, DepartmentsModel mainModel)
    {
        setLayout(new GridBagLayout());
        setOpaque(false);

        this.title = title;
        this.mainController = mainController;
        this.mainModel = mainModel;

        createElements();
        styleElements();
        addElements();
        addListeners();
    }

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
    protected void loadImages() {

    }

    @Override
    protected void createElements()
    {
        // PANELS
        jpAccessInfo     = new JPanel();
        jpButtons        = new JPanel();

        // LABELS
        // Access Info
        jlAccessInfo = new SubHeaderLabel("Access Information");
        jlUsername = new DefaultLabel("Username:");
        jlPassword = new DefaultLabel("Password:");

        // INPUT FIELDS
        // Access Info
        jtfUsername = new InputWPlaceholder(20, "Username...");
        jtfUsername.setName("Username");

        jpfPassword = new PasswordInputWPlaceholder(20, "Password...");
        jpfPassword.setName("Password");

        // BUTTONS
        jbLogin = new DefaultButton("Submit", "SaveDepartment");
    }

    @Override
    protected void styleElements()
    {
        // Layout managers
        jpAccessInfo.setLayout(new GridBagLayout());
        jpButtons.setLayout(new GridBagLayout());

        jpfPassword.setEchoChar('*');

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setOpaque(true);
    }

    @Override
    protected void addElements()
    {
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
        addToGrid(jpAccessInfo, jpfPassword,
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
        addToGrid(jpButtons, jbLogin,
                0, 0,
                0, 0,
                1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER,
                null, 5, 5);

        // ----------------  Add Form Container Panels ------------------
        addToGrid(this, jpAccessInfo,
                0, 0,
                0, 0,
                1, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);

        addToGrid(this, jpButtons,
                0, 1,
                0, 0,
                2, 1,
                GridBagConstraints.HORIZONTAL, null,
                new Insets(0, 10, 15, 10), null, null);
    }

    public JPanel[] getInputsContainers() {
        return new JPanel[]{jpAccessInfo};
    }

    @Override
    protected void addListeners()
    {
        jbLogin.addActionListener(this.mainController);
    }
}
