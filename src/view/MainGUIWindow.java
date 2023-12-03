package view;

import control.Constants;
import control.MainGUIWindowControl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import view.components.*;

import static control.Constants.MAINCOLOR;

public class MainGUIWindow extends JFrame  {
    //view with a table, menu bar with sorting/filtering options and button to add tasks
    private JPanel contentPane;
    private JButton addBttn;
    private JTable taskTable;
    private JMenuBar menuBar;
    private JMenu listMenu;
    private JMenuItem filterMenuItem;
    private JMenuItem arrangeMenuItem;
    private JMenuItem changeDateMenuItem;
    private JMenu dateMenu;
    private JMenuItem todayMenuItem,yesterdayMenuItem,tomorrowMenuItem;
    private JMenuItem showAll1;
    private JMenuItem showAll2;
    private JButton closeButton;

    public MainGUIWindow() throws HeadlessException, IOException, ClassNotFoundException {
        setContentPane(contentPane);
        setSize(600,600);
        //setTitle(TEXTBUNDLE.getString("title"));
        setLocationByPlatform(true);
        //hides default titlebar: our jmenubar is going to work as one.
        setUndecorated(true);
        customMenuBar();
        setJMenuBar(menuBar);
        actionCommands();
        //controller for the logic parts of the window
        new MainGUIWindowControl(this);
    }


    //VISUAL ADJUSTMENTS
    private void customMenuBar() {
        JMenu[] menus = {dateMenu, listMenu};
        //makes jMenus inherit menubar background color (not working TODO)
        for (JMenu menu:menus) menu.setOpaque(false);
        for (int i=0;i<80;i++) menuBar.add(Box.createHorizontalGlue());
        closeButton=new CloseButton();
        menuBar.add(closeButton);
    }
    private void createUIComponents() {
        // makes menubar use custom class: allows us to set the color + use it as the title bar
        this.menuBar=new ColoredMenuBar(this);
        this.menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.LINE_AXIS));
        ((ColoredMenuBar) menuBar).setBgColor(MAINCOLOR);
        menuBar.setBorder(new LineBorder(MAINCOLOR));
        customButtons();
    }

    private void actionCommands() {
        filterMenuItem.setActionCommand("filter");
        todayMenuItem.setActionCommand("today");
        yesterdayMenuItem.setActionCommand("yesterday");
        tomorrowMenuItem.setActionCommand("tomorrow");
        changeDateMenuItem.setActionCommand("changedate");
        showAll1.setActionCommand("showall");
        showAll2.setActionCommand("showall");
    }

    private void customButtons() {
        addBttn = new ColoredButton(Constants.TEXTBUNDLE.getString("add"));
    }

    //GETTERS-> used in Control class
    public JMenuItem[] getMenuItems(){
        JMenuItem[] items ={filterMenuItem,showAll1,todayMenuItem,yesterdayMenuItem,
        tomorrowMenuItem,changeDateMenuItem,showAll2};
        return items;
    }
    public JButton getCloseButton() {
        return closeButton;
    }
    //this uses a custom name to avoid overriding another method
    public JMenuBar getMyMenuBar() {
        return menuBar;
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
}
