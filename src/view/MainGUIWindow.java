package view;

import control.Constants;
import control.MainGUIWindowControl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

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
        return new JMenuItem[]{filterMenuItem,showAll1,todayMenuItem,yesterdayMenuItem,
        tomorrowMenuItem,changeDateMenuItem,showAll2};
    }
    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getAddBttn() {
        return addBttn;
    }

    public JTable getTaskTable() {
        return taskTable;
    }


    public JMenuItem getFilterMenuItem() {
        return filterMenuItem;
    }


    public JMenuItem getChangeDateMenuItem() {
        return changeDateMenuItem;
    }


    public JMenuItem getShowAll1() {
        return showAll1;
    }

    public JMenuItem getShowAll2() {
        return showAll2;
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }
}
