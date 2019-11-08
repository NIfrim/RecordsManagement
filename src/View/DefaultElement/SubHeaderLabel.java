package View.DefaultElement;

import javax.swing.*;
import java.awt.*;

public class SubHeaderLabel extends JLabel {

    public SubHeaderLabel() {
        this("Missing Name");
    }

    public SubHeaderLabel(String text) {
        super(text);
        setDefaultStyle();
    }

    private void setDefaultStyle() {
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));
        this.setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
