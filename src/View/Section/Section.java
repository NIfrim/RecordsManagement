package View.Section;

import Controllers.FormController;
import Controllers.SubnavController;
import Core.Controller;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Section extends View {

    protected SubNav jpSubNavigation;
    protected JPanel jpSectionContainer;
    protected JPanel jpFilter;
    protected JPanel jpTitle;

    protected Font titleFont;

    protected JLabel jlTitle;
    protected ArrayList<JLabel> jlBreadcrumbs;

    protected SubnavController subnavController;
    protected FormController   formController;

    // Section Models
    protected Model mainModel;
    protected HashMap<String, Model> otherModels;

    // DefaultElement constructor
    public Section() {

        this(null);
    }

    public Section(Controller mainController) {

        this("", mainController, null);
    }

    public Section(String title, Controller mainController, Model mainModel) {

        super(title, mainController);
        this.subnavController = new SubnavController();
        this.mainModel = mainModel;

        // PANELS
        jpSectionContainer = new JPanel();
        subnavController.addView(jpSectionContainer);

        jpSubNavigation = new SubNav(null);
        jpTitle = new JPanel();
        jpFilter = new JPanel();

        try {
            titleFont = new Font("Calibri", Font.BOLD, 20);
        } catch (Exception e) {
            titleFont = new Font("LucidaSans", Font.ITALIC, 20);
        }

        jlTitle = new JLabel(title.toUpperCase());
        jpTitle.add(jlTitle);

        setDefaultStyle();
        setDefaultState();
    }

    private void setDefaultState() {
    // ADD MAIN PANELS WHICH APPLY TO EACH NEW SECTION
        addToGrid(this, jpTitle,
                1, 1,
                0.9, 0,
                1, 1,
                GridBagConstraints.BOTH, GridBagConstraints.LINE_START,
                new Insets(15, 15, 0, 15), null, null);

        addToGrid(this, jpSubNavigation,
                1, 2,
                0.9, 0,
                1, 1,
                GridBagConstraints.BOTH, GridBagConstraints.LINE_START,
                new Insets(0, 15, 0, 15), null, null);

        addToGrid(this, jpSectionContainer,
                1, 3,
                0.9, 1,
                1, 1,
                GridBagConstraints.BOTH, GridBagConstraints.LINE_START,
                new Insets(0, 15, 15, 15), null, null);
    }

    private void setDefaultStyle() {

        jlTitle.setFont(titleFont);
        jlTitle.setForeground(Color.WHITE);

        setLayout(new GridBagLayout());
        jpSubNavigation.setLayout(new BoxLayout(jpSubNavigation, BoxLayout.X_AXIS));
        jpSectionContainer.setLayout(new CardLayout());

        setOpaque(false);
        jpSubNavigation.setOpaque(true);
        jpTitle.setOpaque(false);

        jpSubNavigation.setBackground(Color.GRAY);
        jpSectionContainer.setBackground(Color.WHITE);
    }

    public Component[] getSubsections() {
        return this.jpSectionContainer.getComponents();
    }

    @Override
    protected abstract void loadImages();

    @Override
    protected abstract void createElements();

    @Override
    protected abstract void styleElements();

    @Override
    protected abstract void addElements();

    @Override
    protected abstract void addListeners();

    protected void setMaxSize(Component[] components, int width, int height) {

        for (Component component:components) {

            if (component instanceof JComboBox) {

                component.setMaximumSize(new Dimension(width, height));
            }
        }
    }
}
