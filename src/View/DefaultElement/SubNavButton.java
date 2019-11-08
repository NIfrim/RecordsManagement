package View.DefaultElement;

import javax.swing.*;
import java.awt.Color;

public class SubNavButton extends JButton {

    private Color defaultColor;

    public SubNavButton(String label)
    {
        super(label);
        this.defaultColor = Color.LIGHT_GRAY;

        setDefaultStyle();
    }

    private void setDefaultStyle() {

        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(defaultColor);
        setFocusable(false);
    }

    public Color getDefaultColor() {
        return defaultColor;
    }
}
