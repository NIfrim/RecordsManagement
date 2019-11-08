package Controllers;

import Core.Application;
import Core.Controller;
import View.Navigation.Buttons.NavButton;
import View.Navigation.SideNavigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NavController extends Controller {

    private Application application;

    public void setApplication(Application application) { this.application = application; }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        String sectionName = ((JButton) source).getText();

        // Collapse all nav buttons.
        for (Component component: ((SideNavigation) this.mainViewObj).getNavButtons()) {

            if (component instanceof JButton) {
                int curWidth = component.getWidth();
                if (curWidth > 105) {
                    component.setPreferredSize(new Dimension(105, 30));
                    component.setSize(105, 30);
                }
            }
        }

        if (((JButton) source).getText().equals("Log Out"))
        {
            application.setState("LoggedOut");

            application.getFrame().revalidate();
            application.getFrame().repaint();
        }
        else
        {
            // Switch to related section.
            CardLayout cl = ((CardLayout) viewContainers.get("mainContent").getLayout());
            cl.show(viewContainers.get("mainContent"), sectionName);

            // Extend nav button only after section has been switched to
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Extend clicked button
                    ((NavButton) source).setPreferredSize(new Dimension(115, 30));
                    ((NavButton) source).setSize(115, 30);
                }
            });
        }
    }
}
