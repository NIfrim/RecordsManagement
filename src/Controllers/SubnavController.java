package Controllers;

import Core.Controller;
import View.DefaultElement.SubNavButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class SubnavController extends Controller {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        String sectionName = ((JButton) source).getText();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Switch to related section.
                CardLayout cl = ((CardLayout) mainViewObj.getLayout());
                cl.show(mainViewObj, sectionName);
            }
        });

        // Switch active button. First set the default color to all sub-nav buttons.
        for(Map.Entry<String, Component> element : this.viewComponents.entrySet()) {
            SubNavButton button = ((SubNavButton) element.getValue());
            button.setBackground(button.getDefaultColor());
        }
        // Set background to clicked button.
        ((JButton) source).setBackground(Color.WHITE);
    }
}
