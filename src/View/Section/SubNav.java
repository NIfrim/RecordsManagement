package View.Section;

import Controllers.SubnavController;
import Core.View;

import javax.swing.*;
import java.awt.*;

public class SubNav extends View {

    public SubNav(SubnavController mainController) {
        this.mainController = mainController;
    }

    @Override
    protected void loadImages() {

    }

    @Override
    protected void createElements() {

    }

    @Override
    protected void styleElements() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    @Override
    protected void addElements() {

    }

    @Override
    protected void addListeners() {

    }
}
