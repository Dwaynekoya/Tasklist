package control;

import java.io.*;
import java.util.ArrayList;
/*
* this class handles loading and saving a file that contains an arraylist
* */
public class FileManagement {
    public static ArrayList loadFile(File file) {
        ArrayList list =null;
        if (file.exists()){
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                list = (ArrayList) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
    public static void saveFile(ArrayList list, File file) {
        if (list!=null&&!list.isEmpty()){
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
