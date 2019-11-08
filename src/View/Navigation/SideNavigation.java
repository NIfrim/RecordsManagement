package View.Navigation;

import Controllers.LoginController;
import Controllers.NavController;
import Core.View;
import Models.Entities.Department;
import View.Navigation.Buttons.*;
import javax.swing.*;
import java.awt.*;

public class SideNavigation extends View {

    // COLOURS
    private Color bricky;

    // PANELS
    private JPanel jpNavContainer;
    private JPanel jpUnderNavContainer;
    private JPanel jpLogo;
    private JPanel jpButtons;
    private JPanel jpHelp;

    // LABELS
    private JLabel jlLogo;

    // NAV BUTTONS
    private NavButton jbLecturers;
    private NavButton jbDepartments;
    private JButton   jbLogout;


    // IMAGES
    private Image iLogo;

    // BORDERS

    // OTHERS
    private LayoutManager overlayLayout;
    private LoginController loginController;

    /** @param navController is the action controller which jpSideNav is going to use.
     * */
    public SideNavigation(NavController navController, Department departmentLoggedIn) {

        this.mainController = navController;
        this.departmentLoggedIn = departmentLoggedIn;

//        loadImages();
        createElements();
        styleElements();

        addElements();
        addListeners();
    }

    @Override
    protected void loadImages() {
        // IMAGES
        ImageIcon iiLogo;

        try {
            // The the Images as a resource and store them.
            iiLogo = new ImageIcon(Toolkit.getDefaultToolkit().createImage(SideNavigation.class.getResource("images/logo-small.png")));
        }
        catch (Exception e) {
            // Load placeholder image if original image cannot be loaded.
            iiLogo = new ImageIcon(Toolkit.getDefaultToolkit().createImage(SideNavigation.class.getResource("images/placeholder.png")));
        }

        // Scale the Images and store the scaled version
        iLogo = iiLogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    }

    @Override
    protected void createElements()
    {
        // COLOR
        bricky = new Color(207, 152, 119);

        // PANELS
        jpNavContainer = new JPanel();
        jpUnderNavContainer = new JPanel();
        jpLogo = new JPanel();
        jpButtons = new JPanel();
        jpHelp = new JPanel();

        // LABELS
        jlLogo = new JLabel();

        // NAV BUTTONS
        jbLecturers   = new NavButton("Lecturers ", bricky);
        jbDepartments = new NavButton("Departments ", bricky);
        jbLogout      = new JButton("Log Out");

        // OTHER
        overlayLayout = new OverlayLayout(this);
        loginController = new LoginController();
    }

    @Override
    protected void styleElements()
    {
        setLayout(overlayLayout);
        setPreferredSize(new Dimension(120, Integer.MAX_VALUE));
        setBackground(new Color(45, 73, 114));
        setOpaque(false);

        jpButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpNavContainer.setLayout(new BoxLayout(jpNavContainer, BoxLayout.Y_AXIS));

        jpHelp.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
        jpButtons.setPreferredSize(new Dimension(Integer.MAX_VALUE, 400));
        jpLogo.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));

        jpLogo.setMaximumSize(new Dimension(100, 100));
        jpUnderNavContainer.setMaximumSize(new Dimension(130, Integer.MAX_VALUE));
        jpButtons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));

        jpUnderNavContainer.setBackground(Color.DARK_GRAY);

        jpNavContainer.setOpaque(false);
        jpLogo.setOpaque(false);
        jpButtons.setOpaque(false);
        jpHelp.setOpaque(false);

        jbLogout.setPreferredSize(new Dimension(120, 80));
        jbLogout.setSize(120, 80);
        jbLogout.setBackground(new Color(250, 152, 119));

        // Controls how far the background under the nav buttons is from the left edge.
        jpUnderNavContainer.setAlignmentX(0.6f);
        jpLogo.setAlignmentX(0.6f);
    }

     /**
     @param components is an Array of components
     Function loops through the components passed
     Looks for JButton components
     Sets size for each
     */
    private void resizeComponents(Component[] components)
    {
        for (Component component:components) {

            if (component instanceof JButton) {
                component.setPreferredSize(new Dimension(105, 30));
            }
        }
        jbLecturers.setPreferredSize(new Dimension(120, 30));
        jbLecturers.setSize(115, 30);
    }

    // Function adds all the elements to the SideNavigation Panel.
    @Override
    protected void addElements() {

        // Add Nav section buttons
        if (departmentLoggedIn.getDepartmentType().equals("Administration"))
        {
            jpLogo.add(jlLogo, BorderLayout.CENTER);
            jpButtons.add(jbLecturers);
            jpButtons.add(jbDepartments);
        }
        else
        {
            jpLogo.add(jlLogo, BorderLayout.CENTER);
            jpButtons.add(jbLecturers);
        }

        jpLogo.add(jbLogout, BorderLayout.CENTER);

        // Add the containers with the elements
        jpNavContainer.add(jpLogo);
        jpNavContainer.add(Box.createVerticalStrut(50));   // Padding area which resizes with window size
        jpNavContainer.add(jpButtons);
        jpNavContainer.add(Box.createVerticalGlue());   // Padding area which resize with window size
        jpNavContainer.add(jpHelp);

        // Add the parent containers previously filled
        add(jpNavContainer);
        add(jpUnderNavContainer);

        // Call to resize components
        // Used for resizing navigation buttons
        resizeComponents(jpButtons.getComponents());
    }

    @Override
    protected void addListeners() {
        Component[] navComponents = this.getNavButtons();

        for (Component component:navComponents) {

            if (component instanceof JButton)
                ((JButton) component).addActionListener(this.mainController);
        }

        jbLogout.addActionListener(mainController);
    }

    public Component[] getNavButtons() {
        return jpButtons.getComponents();
    }
}
