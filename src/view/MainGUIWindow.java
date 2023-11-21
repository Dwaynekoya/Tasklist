package view;

import control.Constants;
import control.TaskListManagement;
import control.TaskTableModel;
import model.Task;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static control.Constants.TEXTBUNDLE;

public class MainGUIWindow extends JFrame implements ActionListener {
    private JButton removeBttn;
    private JButton addBttn;
    private JTable taskTable;
    private JMenuBar menuBar;
    private JMenu listMenu;
    private JMenuItem filterMenuItem;
    private JMenuItem arrangeMenuItem;
    private JMenuItem changeDateMenuItem;
    private JMenu dateMenu;
    private JMenuItem todayMenuItem;
    private JMenuItem yesterdayMenuItem;
    private JMenuItem tomorrowMenuItem;
    private JPanel contentPane;
    private AbstractTableModel model;
    private final String commandAdd = TEXTBUNDLE.getString("add");
    private final String commandRemove = TEXTBUNDLE.getString("remove");

    public MainGUIWindow() throws HeadlessException, IOException, ClassNotFoundException {
        setContentPane(contentPane);
        setSize(600,600);
        setTitle(TEXTBUNDLE.getString("title"));
        setJMenuBar(menuBar);
        configTable();
        this.addBttn.setActionCommand(commandAdd);
        this.removeBttn.setActionCommand(commandRemove);
        this.addBttn.addActionListener(this);
        this.removeBttn.addActionListener(this);
    }

    private void configTable() throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = TaskListManagement.getTasklist();
        if (tasks==null){
            model = new TaskTableModel();
        } else {
            model = new TaskTableModel(tasks);
        }
        this.taskTable.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand){
            case commandAdd:
                new CreateNewTaskGUI();
                break;
            case commandRemove:
                //TODO:REMOVE
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + actionCommand);
        }
    }
}
