import view.MainGUIWindow;

import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("text");
        System.out.println(resourceBundle.getString("title"));
        new MainGUIWindow().setVisible(true);
    }
}