package view;

import control.Constants;
import control.TaskTableModel;
import model.Habit;
import model.Task;
import view.components.CloseButton;
import view.components.CustomTitleBar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TaskGUI extends JDialog {
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
    private CustomTitleBar customTitleBar;
    private JLabel daysLabel;
    private JButton deleteButton;
    private CloseButton closeButton;
    private JToggleButton toggleDone;
    private final TaskTableModel model;
    private DefaultComboBoxModel comboBoxModel;
    private ArrayList<String> types;
    private Task task;
    private int index;

    public TaskGUI(TaskTableModel model, int index) {
        this.index=index;
        task= model.gettasklist().get(index);
        setContentPane(contentPane);
        //default size would cut the hidden fields when shown unless we explicitly make the window bigger
        setMinimumSize(new Dimension(330,330));
        setResizable(false);
        setUndecorated(true);
        //centers the window
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.model=model;
        styleTextFields();
        spinnerDateFormat();
        comboBoxModel();
        //hides an input field until togglebutton is pressed
        defaultButtons();
        //shows data from the selected task
        loadData();
        pack();
    }

    private void loadData() {
        this.textFieldName.setText(task.getName());
        this.textAreaDetails.setText(task.getDetails());
        this.sliderPriority.setValue(task.getPriority());
        this.comboBoxType.setSelectedItem(task.getType());
        this.spinnerDate.setValue(task.getDate());
        this.toggleDone.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (toggleDone.isSelected()) {
                    toggleDone.setText(Constants.TEXTBUNDLE.getString("yes"));
                }else {toggleDone.setText(Constants.TEXTBUNDLE.getString("no"));}
            }
        });

        boolean habit = task instanceof Habit;
        if (habit) {
            showRepeat(true);
            toggleHabit.setSelected(true);
            toggleHabit.setText(Constants.TEXTBUNDLE.getString("yes"));
            this.spinnerRepeat.setValue(((Habit)task).getRepeatEveryX());
        }
        if (task.isDone()) toggleDone.setSelected(true); toggleDone.setText(Constants.TEXTBUNDLE.getString("yes"));
    }

    private void defaultButtons() {
        showRepeat(false);
        toggleButtonHabit();
        okCancelButtons();
        deleteButton.addActionListener(e -> {
            model.remove(index);
            dispose();
        });
    }

    //preparing a model for the combobox so we can both load the existing types + add new ones
    private void comboBoxModel() {
        //loads the different types for the tasks
        //loadTypes();
        //System.out.println(types.toArray());
        //comboBoxModel = new DefaultComboBoxModel<>(types.toArray());
        comboBoxModel = new DefaultComboBoxModel<>(Constants.TYPES);
        //allows the user to write their own Type in the combobox
        comboBoxType.setEditable(true);
        comboBoxType.setModel(comboBoxModel);
    }

    private void loadTypes() {
        /*if (Constants.TYPESFILE.exists()){
            types = (ArrayList<String>) FileManagement.loadFile(Constants.TYPESFILE);
        } else {
            types=new ArrayList<>();
        }*/

        //types.add(Constants.TEXTBUNDLE.getString("default"));
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
        labelRepeat.setVisible(shown);
        spinnerRepeat.setVisible(shown);
        daysLabel.setVisible(shown);
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
        task = newTask;
        //TODO: no funca
        model.fireTableRowsUpdated(index,index);
        dispose();
    }

    private Task receiveInput() {
        Task newTask = null;
        String name = this.textFieldName.getText();
        String details = this.textAreaDetails.getText();
        int priority = this.sliderPriority.getValue();
        String type = (String) this.comboBoxType.getSelectedItem();
        //adds type to the list if it isnt there already
        /*if (!types.contains(type)) {
            System.out.println(type);
            //TODO: FIX: DOESNT DO IT
            comboBoxModel.addElement(type);
        }*/
        boolean habit = this.toggleHabit.isSelected();
        int repeat = (int) this.spinnerRepeat.getValue();
        Date date = (Date) this.spinnerDate.getValue();
        //new task or habit
        if (habit){
            newTask = new Habit(name,details,priority,type,date,repeat);
        } else {
            newTask = new Task(name, details, priority, type, date);
        }
        //save types to serializable file
        //FileManagement.saveFile(types, Constants.TYPESFILE);
        return newTask;
    }



    private void onCancel() {
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
        pack();
    }

    private void createUIComponents() {
        customTitleBar =new CustomTitleBar(this);
    }
}
