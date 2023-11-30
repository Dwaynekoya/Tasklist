package control;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

public class Constants {
    //resource bundle for all text in the app
    public static final ResourceBundle TEXTBUNDLE = ResourceBundle.getBundle("text");
    //table column names
    public static final String[] DEFAULLT_COLUMNS = {TEXTBUNDLE.getString("name"),
            TEXTBUNDLE.getString("type"),
            TEXTBUNDLE.getString("priority"),
            TEXTBUNDLE.getString("done")
    };
    //serializable file for tasks
    public static final File TASKFILE = new File("./tasks.ser");
    //serializable file for task TYPES:
    public static final File TYPESFILE = new File("./types.ser");
    //serializable file for USERS:
    public static final File USERFILE = new File("./users.ser");
    public static final String DATEFORMAT= "dd/MM/yyyy";
    public static final Color MAINCOLOR = Color.CYAN;
    //border set for all text inputs
    public static final Border INPUTBORDER = BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK);
}
