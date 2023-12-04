package control;

import model.Task;
import view.*;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

public class MainGUIWindowControl implements ActionListener {
    private final MainGUIWindow window;
    private TaskTableModel model;
    private JTable taskTable;
    private TableRowSorter<TaskTableModel> sorter;

    public MainGUIWindowControl(MainGUIWindow window) {
        this.window = window;
        findComponents();
        configTable();
        closeWithEscape();
    }

    private void closeWithEscape() {
       window.dispose();
    }

    private void configTable() {
        //loading the tasklist and setting it in the model
        ArrayList<Task> tasks = FileManagement.loadFile(Constants.TASKFILE);
        if (tasks==null) tasks=new ArrayList<>();
        model = new TaskTableModel(tasks);
        this.taskTable.setModel(model);
        defaultOrderTable();
        //close the window when using escape key
        //window.getContentPane().registerKeyboardAction(e -> closeWithEscape(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void changeFilter(String regex, int index){
        RowFilter<TaskTableModel, Integer> rf = RowFilter.regexFilter(regex,index);
        sorter.setRowFilter(rf);
    }
    private void removeFilter(){
        sorter.setRowFilter(null);
    }
    private void defaultOrderTable() {
        sorter=new TableRowSorter<>(this.model);
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
        //buttons
        JButton addBttn = window.getAddBttn();
        configMenuListeners();
        JButton closeButton = window.getCloseButton();
        //setting action commands here
        addBttn.setActionCommand("add");
        addBttn.addActionListener(this);
        closeButton.addActionListener(this);
    }

    private void configMenuListeners() {
        for (JMenuItem item: window.getMenuItems()){
            item.addActionListener(this);
        }
        //MNEMONIC
        window.getFilterMenuItem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK));
        window.getChangeDateMenuItem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
        window.getShowAll1().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        window.getShowAll2().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.TABLEDATEFORMAT);
        switch (actionCommand){
            case "add":
                new CreateNewTaskGUI(model).setVisible(true);
                break;
            case "filter":
                new FilterDialog(this).setVisible(true);
                break;
            case "close":
                ArrayList tasks = model.gettasklist();
                FileManagement.saveFile(tasks, Constants.TASKFILE);
                System.exit(0);
                break;
            case "showall":
                removeFilter();
                break;
            case "today":
                changeFilter(dateFormat.format(Date.from(Instant.now())), 3);
                break;
            case "yesterday":
                Date yesterday = Date.from(LocalDate.now().minusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC));
                changeFilter(dateFormat.format(yesterday), 3);
                break;
            case "tomorrow":
                Date tomorrow = Date.from(LocalDate.now().plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC));
                changeFilter(dateFormat.format(tomorrow), 3);
                break;
            case "changedate":
                new ChangeDateDialog(this).setVisible(true);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + actionCommand);
        }
    }
}
