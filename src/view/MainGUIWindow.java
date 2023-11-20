package view;

import control.FileManagement;
import control.TaskTableModel;
import model.Task;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static control.Constants.TEXTBUNDLE;

public class MainGUIWindow extends JFrame{
    private JButton quitarButton;
    private JButton añadirButton;
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

    public MainGUIWindow() throws HeadlessException, IOException, ClassNotFoundException {
        setContentPane(contentPane);
        setSize(600,600);
        setTitle(TEXTBUNDLE.getString("title"));
        setJMenuBar(menuBar);
        configTable();
    }

    private void configTable() throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = FileManagement.loadFile();
        if (tasks==null){
            model = new TaskTableModel();
        } else {
            model = new TaskTableModel(tasks);
        }
        this.taskTable.setModel(model);
    }
}
