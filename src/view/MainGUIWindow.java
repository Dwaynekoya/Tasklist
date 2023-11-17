package view;

import javax.swing.*;
import java.awt.*;

import static control.Constants.TEXTBUNDLE;

public class MainGUIWindow extends JFrame{
    private JButton quitarButton;
    private JButton añadirButton;
    private JTable table1;
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

    public MainGUIWindow() throws HeadlessException {
        setContentPane(contentPane);
        setSize(600,600);
        setTitle(TEXTBUNDLE.getString("title"));
        setJMenuBar(menuBar);
    }
}
