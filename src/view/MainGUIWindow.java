package view;

import control.FileManagement;
import control.MainGUIWindowControl;
import control.TaskTableModel;
import model.Task;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static control.Constants.TEXTBUNDLE;

public class MainGUIWindow extends JFrame  {
    //view with a table, menu bar with sorting/filtering options and buttons to add/remove tasks
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
    private TaskTableModel model;
    private TableRowSorter<TaskTableModel> sorter;

    public MainGUIWindow() throws HeadlessException, IOException, ClassNotFoundException {
        setContentPane(contentPane);
        setSize(600,600);
        setTitle(TEXTBUNDLE.getString("title"));
        setJMenuBar(menuBar);
        //setting action commands here to avoid not using Final strings
        this.addBttn.setActionCommand("add");
        this.removeBttn.setActionCommand("remove");
        //controller for the logic parts of the window
        new MainGUIWindowControl(this);
    }
    //GETTERS-> used in Control class
    //this uses a custom name to avoid overriding another method
    public JMenuBar getMyMenuBar() {
        return menuBar;
    }

    public JButton getRemoveBttn() {
        return removeBttn;
    }

    public JButton getAddBttn() {
        return addBttn;
    }

    public JTable getTaskTable() {
        return taskTable;
    }



    public JMenu getListMenu() {
        return listMenu;
    }

    public JMenuItem getFilterMenuItem() {
        return filterMenuItem;
    }

    public JMenuItem getArrangeMenuItem() {
        return arrangeMenuItem;
    }

    public JMenuItem getChangeDateMenuItem() {
        return changeDateMenuItem;
    }

    public JMenu getDateMenu() {
        return dateMenu;
    }

    public JMenuItem getTodayMenuItem() {
        return todayMenuItem;
    }

    public JMenuItem getYesterdayMenuItem() {
        return yesterdayMenuItem;
    }

    public JMenuItem getTomorrowMenuItem() {
        return tomorrowMenuItem;
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public TaskTableModel getModel() {
        return model;
    }

    public TableRowSorter<TaskTableModel> getSorter() {
        return sorter;
    }
}
