package Controllers;

import Core.Application;
import Core.Controller;
import Models.Entities.Department;
import View.DefaultElement.InputWPlaceholder;
import View.Section.Login.LoginSection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class LoginController extends Controller {

    private Department matchedDepartment;
    private Application application;

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        HashMap<String, Department> departments = mainDataModel.getDepartmentsList();

        String enteredUsername = ((InputWPlaceholder) ((LoginSection) mainViewObj).getLoginForm().getFormInputs().get("Username")).getText().toLowerCase();
        String enteredPassword = new String(((JPasswordField) ((LoginSection) mainViewObj).getLoginForm().getFormInputs().get("Password")).getPassword());

        for (String key : departments.keySet())
        {
            if (enteredUsername.equals(departments.get(key).getUsername().toLowerCase()))
            {
                if (enteredPassword.equals(departments.get(key).getPassword()))
                {
                    matchedDepartment = departments.get(key);

                    application.setState("LoggedIn");

                    application.getFrame().getContentPane().revalidate();
                    application.getFrame().getContentPane().repaint();
                }
            }
        }
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Department getMatchedDepartment() {
        return matchedDepartment;
    }
}
