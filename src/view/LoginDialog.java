package view;

import control.Constants;
import control.FileManagement;
import model.User;
import view.components.CloseButton;
import view.components.ColoredButton;
import view.components.CustomTitleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton closeButton;
    private CustomTitleBar titleBar;
    private JLabel image;
    private JLabel loginLabel;
    private JLabel usernamelabel;
    private JLabel passwordLabel;
    private JLabel wrongloginLabel;
    private JButton registerButton;
    private ArrayList<User> users;

    public LoginDialog() {
        image.setIcon(new ImageIcon("./resources/images/tasklist.png"));
        setContentPane(contentPane);
        //hides the default titlebar: we are using our own component as one
        setUndecorated(true);
        setModal(true);
        textStyle();
        getRootPane().setDefaultButton(buttonOK);
        buttonOKCancel();
        setMinimumSize(new Dimension(500,getHeight()));
        readUserList();
        pack();
        setLocationRelativeTo(null);

    }

    private void textStyle() {
        loginLabel.setForeground(Constants.DARKMAINCOLOR);
        usernamelabel.setForeground(Constants.DARKMAINCOLOR);
        passwordLabel.setForeground(Constants.DARKMAINCOLOR);
        styleTextFields();
    }

    //STYLE: makes texfield+passwordfield have a line under them instead of the default border
    private void styleTextFields() {
        textField1.setBorder(Constants.INPUTBORDER);
        passwordField1.setBorder(Constants.INPUTBORDER);
    }

    private void onOK() {
        // checks that user exists
        checkUser();
    }

    private void checkUser() {
        User user = readUserInput();
        if (users.contains(user)) {
            User found = users.get(users.indexOf(user));
            if (Arrays.equals(found.getPassword(), user.getPassword())){
                System.out.println("---LOGGING IN---");
                try {
                    new MainGUIWindow().setVisible(true);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                dispose();
            }
        }
        //System.out.println("One or more of the fields are wrong.");
        wrongloginLabel.setVisible(true);
    }

    private User readUserInput() {
        String username = this.textField1.getText();
        char[] password = this.passwordField1.getPassword();
        return new User(username,password);
    }

    private void readUserList() {
        users=new ArrayList<>();
        if (Constants.USERFILE.exists()) users = FileManagement.loadFile(Constants.USERFILE);
        //test
        //User admin = new User("admin", "admin".toCharArray());
        //users.add(admin);
        //final app would have a register option
        //FileManagement.saveFile(users,Constants.USERFILE);
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        titleBar=new CustomTitleBar(this);
        closeButton=new CloseButton();
        registerButton=new ColoredButton(Constants.TEXTBUNDLE.getString("register"));
        buttonOK=new ColoredButton(Constants.TEXTBUNDLE.getString("ok"));
        buttonCancel=new ColoredButton(Constants.TEXTBUNDLE.getString("cancel"));
    }
    private void registerUser() {
        new RegisterDialog(users).setVisible(true);
    }
    private void buttonOKCancel() {
        //own closeButton: closes the app, same as the cancel button
        registerButton.addActionListener(e -> registerUser());
        closeButton.addActionListener(e -> onCancel());
        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

}
