package View.DefaultElement;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel extends JLabel {

    public DefaultLabel() {
        this("Missing Name");
    }

    public DefaultLabel(String text) {
        super(text);
        setDefaultStyle();
    }

    private void setDefaultStyle() {
        this.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.setHorizontalAlignment(SwingConstants.RIGHT);
    }
}
