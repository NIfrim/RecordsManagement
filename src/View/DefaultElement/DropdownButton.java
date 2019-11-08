package View.DefaultElement;

import javax.swing.*;
import java.awt.*;

public class DropdownButton extends JComboBox {

    public DropdownButton() {
        super();

        setLookAndFeel();
        setDefaultStyle();
    }

    private void setLookAndFeel()
    {
        // Setting windows 10 look and feel.
        LookAndFeel defaultLAF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            SwingUtilities.updateComponentTreeUI(this);

            UIManager.setLookAndFeel(defaultLAF);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void setDefaultStyle() {
        setMaximumSize(new Dimension(getMaximumSize().width, 30));
//        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        setOpaque(false);
    }

    public void addDropdownItem(String id, String value, JComboBox<DropdownItem> dropdownObject)
    {
        dropdownObject.addItem(new DropdownItem(id, value));
    }
}
