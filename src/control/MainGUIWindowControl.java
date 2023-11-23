package control;

import model.Task;
import view.CreateNewTaskGUI;
import view.MainGUIWindow;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainGUIWindowControl implements ActionListener {
    private MainGUIWindow window;
    //buttons
    private JButton addBttn, removeBttn;
    private TaskTableModel model;
    private JTable taskTable;
    private TableRowSorter<TaskTableModel> sorter;
    private JMenuBar menuBar;

    public MainGUIWindowControl(MainGUIWindow window) {
        this.window = window;
        findComponents();
        try {
            configTable();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void configTable() throws IOException, ClassNotFoundException {
        //loading the tasklist and setting it in the model
        ArrayList<Task> tasks = FileManagement.loadFile();
        if (tasks==null) tasks=new ArrayList<>();
        model = new TaskTableModel(tasks);
        this.taskTable.setModel(model);
        defaultOrderTable();
    }

    private void defaultOrderTable() {
        sorter=new TableRowSorter<TaskTableModel>(this.model);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        //DEFAULT: order by taskname
        sortKeys.add(new RowSorter.SortKey(0,SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.setSortsOnUpdates(true);
        taskTable.setRowSorter(sorter);
    }


    private void findComponents() {
        this.taskTable = window.getTaskTable();
        this.model = window.getModel();
        this.addBttn = window.getAddBttn();
        this.addBttn.addActionListener(this);
        this.removeBttn = window.getRemoveBttn();
        this.removeBttn.addActionListener(this);
        this.menuBar=window.getMyMenuBar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand){
            case "add":
                new CreateNewTaskGUI(model).setVisible(true);
                break;
            case "remove":
                //TODO:REMOVE

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + actionCommand);
        }
    }
}
