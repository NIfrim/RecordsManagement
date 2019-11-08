import Core.Application;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Application app = new Application();
        JFrame frame = app.getFrame();
        frame.setVisible(true);
    }
}
