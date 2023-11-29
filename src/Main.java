import view.LoginDialog;
import view.MainGUIWindow;

import javax.swing.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            /*LoginDialog login = new LoginDialog();
            login.pack();
            login.setVisible(true);*/
            new MainGUIWindow().setVisible(true);
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        //TODO: Custom jmenubar
        //https://stackoverflow.com/questions/15648030/change-background-and-text-color-of-jmenubar-and-jmenu-objects-inside-it
    }
}