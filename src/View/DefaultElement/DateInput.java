package View.DefaultElement;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateInput extends JFormattedTextField {

    private String placeholder;

    public DateInput(String placeholder) {

        this.placeholder = placeholder;

        DateFormat dateFormat = new SimpleDateFormat(placeholder);
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        setFormatter(dateFormatter);

        setPlaceholder(placeholder);
        setDefaultStyle();
    }

    private void setDefaultStyle() {
        setPreferredSize(new Dimension(100, 30));
        this.setMargin(new Insets(5, 5, 5, 5));
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }


    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        // If no placeholder set, or text is typed, break from function.
        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        // Placeholder Font and Size goes in here.
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.GRAY);
        g.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }
}
