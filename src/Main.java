import view.MainGUIWindow;

import java.io.IOException;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        try {
            new MainGUIWindow().setVisible(true);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}