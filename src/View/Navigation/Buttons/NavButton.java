package View.Navigation.Buttons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NavButton extends JButton {

    private Color color;

    public NavButton(String label, Color color)
    {
        super(label);
        this.color = color;

        Border paddingRight = BorderFactory.createEmptyBorder(0, 0, 0, 7);
        setBackground(color);
        setBorder(paddingRight);
        setHorizontalAlignment(SwingConstants.RIGHT);
        setFocusable(false);
    }
}
