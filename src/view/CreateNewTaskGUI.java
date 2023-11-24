package view;

import control.Constants;
import control.TaskTableModel;
import model.Task;

import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

public class CreateNewTaskGUI extends JDialog {
    //dialog with a form to add a new Task to the list
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldName;
    private JTextArea textAreaDetails;
    private JSlider sliderPriority;
    private JSpinner spinnerRepeat;
    private JComboBox comboBoxType;
    private JButton btnHabit;
    private JSpinner spinnerDate;
    private JLabel labelRepeat;
    private TaskTableModel model;

    public CreateNewTaskGUI(TaskTableModel model) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.model=model;
        spinnerDateFormat();
        hideUnhideRepeat();
        okCancelButtons();
    }

    private void hideUnhideRepeat() {
        //TODO: Hide repeat options until habit is checked
        labelRepeat.setVisible(false);
        spinnerRepeat.setVisible(false);
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
        // add your code here
        Task newTask = receiveInput();
        //TaskListManagement.addTask(newTask);
        //System.out.println(TaskListManagement.getTasklist());
        model.add(newTask);
        dispose();
    }

    private Task receiveInput() {
        Task newTask = null;
        String name = this.textFieldName.getText();
        String details = this.textAreaDetails.getText();
        int priority = this.sliderPriority.getValue();
        String type = (String) this.comboBoxType.getSelectedItem();
        boolean habit = this.btnHabit.isSelected();
        int repeat = (int) this.spinnerRepeat.getValue();
        Date date = (Date) this.spinnerDate.getValue();
        //TODO: new task depending on habit/common task
        //newTask=new Task(name,details,priority,repeat,type,habit, date);
        return newTask;
    }

    private void onCancel() {
        // add your code here if necessary
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
        pack();
    }
}
