package View.Section.Login;

import Core.Controller;
import Core.View;
import Models.DepartmentsModel;

import javax.swing.*;
import java.awt.*;

public class LoginSection extends View
{
    private LoginForm jpLoginForm;
    private DepartmentsModel mainDataModel;

    public LoginSection(Controller loginController)
    {
        super(null, loginController);

        createElements();
        styleElements();

        addElements();
        addListeners();
    }

    @Override
    protected void loadImages() {

    }

    @Override
    protected void createElements()
    {
        mainDataModel = new DepartmentsModel();
        jpLoginForm = new LoginForm("System Access", mainController, mainDataModel);
        mainController.addView(jpLoginForm);
        mainController.addModel(mainDataModel);
    }

    @Override
    protected void styleElements()
    {
        setLayout(new GridBagLayout());

        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void addElements()
    {
        // Dynamic Padding using glue
        // Top padding
        addToGrid(this, Box.createGlue(),
                0, 0,
                1, 1,
                3, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);
        // Right padding
        addToGrid(this, Box.createGlue(),
                2, 0,
                1, 1,
                1, 3,
                GridBagConstraints.BOTH, null,
                null, null, null);
        // Bottom padding
        addToGrid(this, Box.createGlue(),
                0, 2,
                1, 1,
                3, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);
        // Left padding
        addToGrid(this, Box.createGlue(),
                0, 0,
                1, 1,
                1, 3,
                GridBagConstraints.BOTH, null,
                null, null, null);

        // Login form
        // Bottom padding
        addToGrid(this, jpLoginForm,
                1, 1,
                0, 0,
                1, 1,
                GridBagConstraints.BOTH, null,
                null, null, null);
    }

    public LoginForm getLoginForm() {
        return jpLoginForm;
    }

    @Override
    protected void addListeners() {

    }
}
