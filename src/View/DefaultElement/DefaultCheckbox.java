package View.DefaultElement;

import javax.swing.*;
import java.awt.*;

public class DefaultCheckbox extends JCheckBox {

    public DefaultCheckbox() {
        this("Missing Text");
    }

    public DefaultCheckbox(String label) {
        super(label);

        setDefaultStyle();
    }

    private void setDefaultStyle() {
        this.setMargin(new Insets(3, 3, 3, 3));
        this.setOpaque(false);
    }
}

