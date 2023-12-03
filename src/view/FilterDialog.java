package view;

import control.Constants;
import control.MainGUIWindowControl;
import view.components.CloseButton;
import view.components.ColoredButton;
import view.components.CustomTitleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FilterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea;
    private JRadioButton radioButtonName;
    private JRadioButton radioButtonType;
    private CustomTitleBar customTitleBar;
    private CloseButton closeButton;
    private MainGUIWindowControl windowControl;

    public FilterDialog(MainGUIWindowControl windowControl) {
        this.windowControl=windowControl;
        setContentPane(contentPane);
        setModal(true);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setMinimumSize(Constants.MINIWINDOW);
        contentPane.setBorder(BorderFactory.createLineBorder(Constants.DARKCONTRASTCOLOR));
        getRootPane().setDefaultButton(buttonOK);
        okCancelButtons();
        textArea.setBorder(Constants.INPUTBORDER);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonName);
        buttonGroup.add(radioButtonType);
        pack();
    }



    private void onOK() {
        // add your code here
        int index=0;
        if (radioButtonType.isSelected()) index=1;
        windowControl.changeFilter(textArea.getText(), index);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    private void okCancelButtons() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
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
    private void createUIComponents() {
        customTitleBar=new CustomTitleBar(this);
        buttonOK=new ColoredButton(Constants.TEXTBUNDLE.getString("ok"));
        buttonCancel=new ColoredButton(Constants.TEXTBUNDLE.getString("cancel"));
        ((ColoredButton)buttonCancel).changeColor(Constants.DARKCONTRASTCOLOR);
    }
}
