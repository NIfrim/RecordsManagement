package View.DefaultElement;

import javax.swing.*;
import java.awt.*;

public class PasswordInputWPlaceholder extends JPasswordField {

    private String placeholder;

    public PasswordInputWPlaceholder()
    {
        this(20);
    }

    public PasswordInputWPlaceholder(final int columns) {
        this(columns, "Placeholder");
    }

    public PasswordInputWPlaceholder(final int pColumns, final String placeholder) {
        super(pColumns);
        setPlaceholder(placeholder);
        setDefaultStyle();
    }

    private void setDefaultStyle() {
        this.setMargin(new Insets(5, 5, 5, 5));
        this.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        // If no placeholder set, or text is typed, break from function.
        if (placeholder == null || placeholder.length() == 0 || getPassword().length > 0) {
            return;
        }

        // Placeholder Font and Size goes in here.
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.GRAY);
        g.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
    }

}
