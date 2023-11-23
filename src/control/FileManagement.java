package control;

import java.io.*;
import java.util.ArrayList;
/*
* this class handles loading and saving a file that contains the Task arraylist where tasks are stored
* */
public class FileManagement {
    public static ArrayList loadFile() throws IOException, ClassNotFoundException {
        ArrayList list =null;
        if (Constants.TASKFILE.exists()){
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(Constants.TASKFILE));
            list = (ArrayList) objectInputStream.readObject();
        }
        return list;
    }
    public static void saveFile(ArrayList list) throws IOException {
        if (list!=null&&!list.isEmpty()){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constants.TASKFILE));
            objectOutputStream.writeObject(list);
        }
    }
}
