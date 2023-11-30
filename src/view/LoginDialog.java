package view;

import control.Constants;
import control.FileManagement;
import model.User;
import view.components.CustomTitleBar;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class LoginDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton closeButton;
    private CustomTitleBar titleBar;
    private ArrayList<User> users;

    public LoginDialog() {
        setContentPane(contentPane);
        setModal(true);
        styleTextFields();
        getRootPane().setDefaultButton(buttonOK);

        buttonOKCancel();
    }

    //STYLE: makes texfield+passwordfield have a line under them instead of the default border
    private void styleTextFields() {
        textField1.setBorder(Constants.INPUTBORDER);
        passwordField1.setBorder(Constants.INPUTBORDER);
    }

    private void onOK() {
        // checks that user exists
        checkUser();
        dispose();
    }

    private void checkUser() {
        readUserList();
        User user = readUserInput();
        if (users.contains(user)) {
            System.out.println("---LOGGING IN---");
            try {
                new MainGUIWindow().setVisible(true);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        } else {
            System.out.println("One or more of the fields are wrong.");
        }
    }

    private User readUserInput() {
        String username = this.textField1.getText();
        char[] password = this.passwordField1.getPassword();
        User user = new User(username,password);
        return user;
    }

    private void readUserList() {
        users=new ArrayList<>();
        if (Constants.USERFILE.exists()) users = FileManagement.loadFile(Constants.USERFILE);
        //test
        User admin = new User("admin", "admin".toCharArray());
        users.add(admin);
        //final app would have a register option
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        LoginDialog dialog = new LoginDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        titleBar=new CustomTitleBar(this);
    }
    private void buttonOKCancel() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
}
