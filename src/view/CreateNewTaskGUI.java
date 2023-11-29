package view;

import control.Constants;
import control.FileManagement;
import control.TaskTableModel;
import model.Habit;
import model.Task;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private JSpinner spinnerDate;
    private JLabel labelRepeat;
    private JToggleButton toggleHabit;
    private TaskTableModel model;
    private DefaultComboBoxModel comboBoxModel;
    private ArrayList<String> types;

    public CreateNewTaskGUI(TaskTableModel model) {
        setContentPane(contentPane);
        //TODO: FIX SIZE. THIS DOES NOTHING
        setSize(330,400);
        setResizable(false);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.model=model;
        styleTextFields();
        spinnerDateFormat();
        comboBoxModel();
        //allows the user to write their own Type in the combobox
        comboBoxType.setEditable(true);
        //hides an input field until togglebutton is pressed
        showRepeat(false);
        toggleButtonHabit();
        okCancelButtons();
    }
//preparing a model for the combobox so we can both load the existing types + add new ones
    private void comboBoxModel() {
        //loads the different types for the tasks
        loadTypes();
        System.out.println(types.toArray());
        comboBoxModel = new DefaultComboBoxModel<>(types.toArray());
    }

    private void loadTypes() {
        if (Constants.TYPESFILE.exists()){
            try {
                //loadFile returns an arraylist
                types = (ArrayList<String>) FileManagement.loadFile(Constants.TYPESFILE);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            types=new ArrayList<>();
        }
        types.add(Constants.TEXTBUNDLE.getString("default"));
    }

    //sets the togglebutton so its text changes on click and shows another input field
    private void toggleButtonHabit() {
        toggleHabit.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (toggleHabit.isSelected()) {
                    toggleHabit.setText(Constants.TEXTBUNDLE.getString("yes"));
                    showRepeat(true);
                }
                else {
                    toggleHabit.setText(Constants.TEXTBUNDLE.getString("no"));
                    showRepeat(false);
                }
            }
        });
    }


    private void styleTextFields() {
        textFieldName.setBorder(Constants.INPUTBORDER);
        textAreaDetails.setBorder(Constants.INPUTBORDER);
    }

    private void showRepeat(boolean shown) {
        //TODO: Hide repeat options until habit is checked
        labelRepeat.setVisible(shown);
        spinnerRepeat.setVisible(shown);
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
        // adds task to the model
        Task newTask = receiveInput();
        model.add(newTask);
        dispose();
    }

    private Task receiveInput() {
        Task newTask = null;
        String name = this.textFieldName.getText();
        String details = this.textAreaDetails.getText();
        int priority = this.sliderPriority.getValue();
        String type = (String) this.comboBoxType.getSelectedItem();
        //adds type to the list if it isnt there already
        if (!types.contains(type)) comboBoxModel.addElement(type);
        boolean habit = this.toggleHabit.isSelected();
        int repeat = (int) this.spinnerRepeat.getValue();
        Date date = (Date) this.spinnerDate.getValue();
        //TODO: new task depending on habit/common task
        if (habit){
            newTask = new Habit(name,details,priority,type,date,repeat);
        } else {
            newTask = new Task(name, details, priority, type, date);
        }
        saveTypes();
        return newTask;
    }

    private void saveTypes() {
        try {
            FileManagement.saveFile(types, Constants.TYPESFILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        pack();
    }
}
