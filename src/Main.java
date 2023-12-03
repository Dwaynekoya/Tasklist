import view.LoginDialog;
import view.MainGUIWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Font defaultFont = Font.createFont(Font.TRUETYPE_FONT,new File("./Roboto-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(defaultFont);
            UIManager.getLookAndFeelDefaults()
                    .put("defaultFont", defaultFont);
            //TODO: Splash screen?
            new LoginDialog().setVisible(true);
            //new MainGUIWindow().setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException | FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}