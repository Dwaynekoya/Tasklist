package view;

import control.Constants;
import control.MainGUIWindowControl;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChangeDateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner spinnerDate;
    private MainGUIWindowControl windowControl;

    public ChangeDateDialog(MainGUIWindowControl windowControl) {
        this.windowControl =windowControl;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        spinnerDateFormat();
        okCancelButtons();
        pack();
    }
    private void spinnerDateFormat() {
        //modelo para que el spinner sea de fechas:
        //spinnerFecha.setModel(new SpinnerDateModel());
        Date datenow = Calendar.getInstance().getTime();
        //SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
        SpinnerDateModel smb = new SpinnerDateModel();
        spinnerDate.setModel(smb);
        JSpinner.DateEditor d = new JSpinner.DateEditor(spinnerDate, Constants.DATEFORMAT);
        spinnerDate.setEditor(d);
        spinnerDate.setValue(datenow);
    }
    private void onOK() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.TABLEDATEFORMAT);
        windowControl.changeFilter(dateFormat.format(spinnerDate.getValue()),3);
        dispose();
    }

    private void onCancel() {
        dispose();
    }
    private void okCancelButtons() {
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
