package control;

import java.util.ResourceBundle;

public class Constants {
    public static final ResourceBundle TEXTBUNDLE = ResourceBundle.getBundle("text");
    public static final String[] defaulltColumns = {TEXTBUNDLE.getString("name"),
            TEXTBUNDLE.getString("type"),
            TEXTBUNDLE.getString("priority"),
            TEXTBUNDLE.getString("done")
    };
}
