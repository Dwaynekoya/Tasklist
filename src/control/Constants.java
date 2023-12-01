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
    //TASK TYPES
    public static final String[] TYPES = {TEXTBUNDLE.getString("default"),
            TEXTBUNDLE.getString("home"), TEXTBUNDLE.getString("work")};
    //serializable file for USERS:
    public static final File USERFILE = new File("./users.ser");
    public static final String DATEFORMAT= "dd/MM/yyyy";
    //Colors:
    public static final Color MAINCOLOR = new Color(131,197,190);
    public static final Color DARKMAINCOLOR = new Color(0,109,119);
    public static final Color LIGHTCONTRASTCOLOR = new Color(255,221,210);
    public static final Color DARKCONTRASTCOLOR = new Color(226,149,120);
    public static final Color TINTEDGRAY = new Color(237,246,249);
    //border set for all text inputs
    public static final Border INPUTBORDER = BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK);
}
