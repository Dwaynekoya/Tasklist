package view;

import control.Constants;
import control.FileManagement;
import model.User;
import view.components.ColoredButton;
import view.components.CustomTitleBar;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RegisterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField;
    private CustomTitleBar customTitleBar;
    private JTextField textField;
    private JLabel userExists;
    private final ArrayList<User> users;

    public RegisterDialog(ArrayList<User> users) {
        this.users=users;
        setContentPane(contentPane);
        contentPane.setBorder(Constants.DIALOGBORDER);
        setUndecorated(true);
        setModal(true);
        setMinimumSize(Constants.MINIWINDOW);
        getRootPane().setDefaultButton(buttonOK);
        styleTextFields();
        okCancelbuttons();
        pack();
        setLocationRelativeTo(null);
    }

    private void styleTextFields() {
        textField.setBorder(Constants.INPUTBORDER);
        passwordField.setBorder(Constants.INPUTBORDER);
    }

    private void onOK() {
        User newUser = new User(textField.getText(),passwordField.getPassword());
        if (!users.contains(newUser)){
            users.add(newUser);
            FileManagement.saveFile(users, Constants.USERFILE);
            dispose();
        }
        userExists.setVisible(true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    private void okCancelbuttons() {
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
    private void createUIComponents() {
        customTitleBar=new CustomTitleBar(this);
        buttonOK=new ColoredButton(Constants.TEXTBUNDLE.getString("ok"));
        buttonCancel=new ColoredButton(Constants.TEXTBUNDLE.getString("cancel"));
    }
}
