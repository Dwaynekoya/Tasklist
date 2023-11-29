package control;

import java.io.*;
import java.util.ArrayList;
/*
* this class handles loading and saving a file that contains an arraylist
* */
public class FileManagement {
    public static ArrayList loadFile(File file) throws IOException, ClassNotFoundException {
        ArrayList list =null;
        if (file.exists()){
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            list = (ArrayList) objectInputStream.readObject();
        }
        return list;
    }
    public static void saveFile(ArrayList list, File file) throws IOException {
        if (list!=null&&!list.isEmpty()){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(list);
        }
    }
}
