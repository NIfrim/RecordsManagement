package Core;

import Controllers.LoginController;
import Controllers.NavController;
import Models.Entities.Department;
import View.Navigation.SideNavigation;
import View.Section.Login.LoginSection;
import View.SectionsContainer;

import javax.swing.*;
import java.awt.*;

public class Application {

    private JFrame frame;

    // Main Containers
    private SectionsContainer jpMainContent;
    private SideNavigation jpSideNav;
    private LoginSection jpLogin;

    // Controllers
    private NavController navController;
    private LoginController loginController;

    public Application() {

        frame = new JFrame("Woodlands Records Management");
        frame.setPreferredSize(new Dimension(960, 720));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);
        frame.pack();

        setState("LoggedOut");
    }

    public void setState(String state)
    {
        switch (state)
        {
            case "LoggedOut":
                createElements("LoggedOut");
                styleElements();
                addLogin();
                break;
            case "LoggedIn":
                createElements("LoggedIn");
                styleElements();
                addElements();
                break;
        }
    }

    private void addLogin()
    {
        try
        {
            removeElements(new Component[]{jpLogin});
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        frame.add(jpLogin, BorderLayout.CENTER);
    }

    private void addElements()
    {
        if (loginController.getMatchedDepartment() != null)
        {
            frame.remove(jpLogin);

            frame.add(jpSideNav, BorderLayout.WEST);
            frame.add(jpMainContent, BorderLayout.CENTER);
        }
    }

    private void styleElements() {
        frame.getContentPane().setBackground(Color.GRAY);
    }

    private void createElements(String state)
    {
        if (state.equals("LoggedOut"))
        {
            loginController = new LoginController();
            loginController.setApplication(this);
            // Instance of the login area
            jpLogin = new LoginSection(loginController);
            loginController.addView(jpLogin);
            loginController.addViewContainers("SectionsContainer", jpMainContent);
        }
        else if (state.equals("LoggedIn"))
        {
            navController = new NavController();
            // Create an instance of the NavController which implements an Action Listener.
            jpSideNav = new SideNavigation(navController, loginController.getMatchedDepartment());
            navController.addView(jpSideNav);
            navController.setApplication(this);

            // Instance of the main sections area.
            jpMainContent = new SectionsContainer(navController, loginController.getMatchedDepartment());
            navController.addViewContainers("mainContent", jpMainContent);
        }
    }

    public JFrame getFrame() { return frame; }

    private void removeElements(Component[] elements)
    {
        Component[] currElements = frame.getContentPane().getComponents();

        for (Component element : currElements)
        {
            for (Component omit : elements) {
                if (element != omit)
                    frame.remove(element);
            }
        }
    }
}
