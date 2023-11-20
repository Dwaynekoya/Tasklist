package control;

import java.io.File;
import java.util.ResourceBundle;

public class Constants {
    public static final ResourceBundle TEXTBUNDLE = ResourceBundle.getBundle("text");
    public static final String[] DEFAULLT_COLUMNS = {TEXTBUNDLE.getString("name"),
            TEXTBUNDLE.getString("type"),
            TEXTBUNDLE.getString("priority"),
            TEXTBUNDLE.getString("done")
    };
    public static final File TASKFILE = new File("./tasks.ser");
}
