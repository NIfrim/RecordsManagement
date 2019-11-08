package View.DefaultElement;

import javax.swing.*;
import java.awt.*;

public class DefaultButton extends JButton {

    private Color backgroundColor;
    private Color foregroundColor;

    public DefaultButton(String buttonText, String buttonName) {
        this(buttonText, buttonName, new Color(100, 200, 200));
    }

    public DefaultButton(String buttonText, String buttonName, Color backgroundColor) {
        this(buttonText, buttonName, backgroundColor, new Color(255, 255, 255));
    }

    public DefaultButton(String buttonText, String buttonName, Color backgroundColor, Color foregroundColor) {

        super(buttonText);

        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;

        this.setName(buttonName);

        setDefaultStyle();
    }

    private void setDefaultStyle() {

        setBackground(this.backgroundColor);
        setForeground(this.foregroundColor);

        setPreferredSize(new Dimension(100, 30));
    }
}
