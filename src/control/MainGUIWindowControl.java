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
    private JButton closeButton;

    public MainGUIWindowControl(MainGUIWindow window) {
        this.window = window;
        findComponents();
        configTable();
    }

    private void configTable() {
        //loading the tasklist and setting it in the model
        ArrayList<Task> tasks = FileManagement.loadFile(Constants.TASKFILE);
        if (tasks==null) tasks=new ArrayList<>();
        model = new TaskTableModel(tasks);
        this.taskTable.setModel(model);
        defaultOrderTable();
    }
    /*
    *  System.out.println("---FILTRAR---");
            //si se pulsa filtrar mientras el campo nombre está vacio se muestran todos los elementos (filtro nulo)
            if (txtNombre.getText().isEmpty()){
                sorter.setRowFilter(null);
            } else {
                //RowFilter<ModeloTablaAlumnos, Integer> rf = RowFilter.regexFilter(txtNombre.getText(),0);
                //si solo quiero que aparezcan los nombres exactos:
                RowFilter<ModeloTablaAlumnos, Integer> rf = RowFilter.regexFilter("^"+txtNombre.getText()+"$",0);
                sorter.setRowFilter(rf);
            }*/

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
        //accesing elements through getters
        this.taskTable = window.getTaskTable();
        this.addBttn = window.getAddBttn();
        this.removeBttn = window.getRemoveBttn();
        this.menuBar=window.getMyMenuBar();
        this.closeButton=window.getCloseButton();
        //setting action commands here
        this.addBttn.setActionCommand("add");
        this.removeBttn.setActionCommand("remove");
        this.addBttn.addActionListener(this);
        this.removeBttn.addActionListener(this);
        this.closeButton.addActionListener(this);
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
            case "close":
                ArrayList tasks = model.gettasklist();
                FileManagement.saveFile(tasks, Constants.TASKFILE);
                System.exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + actionCommand);
        }
    }
}
