import view.LoginDialog;
import view.MainGUIWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.getLookAndFeelDefaults()
                    .put("defaultFont", new Font("Roboto", Font.PLAIN, 14));
            //TODO: Splash screen?
            new LoginDialog().setVisible(true);
            new MainGUIWindow().setVisible(true);
        } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}