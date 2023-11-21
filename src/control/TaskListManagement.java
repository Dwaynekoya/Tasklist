package control;

import model.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskListManagement {
    private static ArrayList<Task> tasklist;

    static {
        try {
            tasklist = FileManagement.loadFile();
            if (tasklist==null) tasklist=new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ;
    public static void addTask(Task task){
        tasklist.add(task);
    }
    public static void removeTask(Task task){
        tasklist.remove(task);
    }
    public static ArrayList<Task> getTasklist() {
        return tasklist;
    }
}
