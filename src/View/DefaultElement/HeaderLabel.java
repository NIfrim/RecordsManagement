package View.DefaultElement;

import javax.swing.*;
import java.awt.*;

public class HeaderLabel extends JLabel {

    public HeaderLabel() {
        this("Missing Name");
    }

    public HeaderLabel(String text) {
        super(text);
        setDefaultStyle();
    }

    private void setDefaultStyle() {
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));
        this.setFont(new Font("Segoe UI", Font.BOLD, 22));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
