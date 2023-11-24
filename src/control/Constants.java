package control;

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
    //serializable file for persistence
    public static final File TASKFILE = new File("./tasks.ser");
    public static final String DATEFORMAT= "dd/MM/yyyy";
    public static final Color MAINCOLOR = Color.CYAN;
}
