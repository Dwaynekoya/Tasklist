package control;

import java.io.*;
import java.util.ArrayList;

public class FileManagement {
    public static ArrayList loadFile() throws IOException, ClassNotFoundException {
        ArrayList list =null;
        if (Constants.TASKFILE.exists()){
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(Constants.TASKFILE));
            list = (ArrayList) objectInputStream.readObject();
        }
        return list;
    }
}
