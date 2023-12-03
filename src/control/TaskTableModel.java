package control;

import model.Task;
import view.TaskGUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//table model for the tasklist

public class TaskTableModel extends AbstractTableModel {
    private ArrayList<Task> tasklist;
    private String[] columns;
    private JToggleButton btnDone;

    public TaskTableModel() {
        this.tasklist=new ArrayList<>();
        columns =Constants.DEFAULLT_COLUMNS;
        btnDone=new JToggleButton(Constants.TEXTBUNDLE.getString("no"));
    }

    public TaskTableModel(ArrayList<Task> Tasks) {
        this.tasklist = Tasks;
        columns =Constants.DEFAULLT_COLUMNS;
    }
    //devuelve boolean según si se añadió o no
    public boolean add(Task Task){
        try {
            return tasklist.add(Task);
        } finally {
            this.fireTableRowsInserted(tasklist.size()-1, tasklist.size()-1);
        }
    }
    //devuelve el Task que ha sido eliminado
    public Task remove(int index){
        Task eliminado = tasklist.remove(index);
        this.fireTableRowsDeleted(index,index);
        return eliminado;
    }

    @Override
    public int getRowCount() {
        return tasklist.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    //necesario para mostrar columnas
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasklist.get(rowIndex);
        switch (columnIndex){
            case 0: return task.getName();
            case 1: return task.getType();
            case 2:
                String priority = "";
                switch (task.getPriority()){
                    case 1->priority=Constants.TEXTBUNDLE.getString("low");
                    case 2->priority=Constants.TEXTBUNDLE.getString("medium");
                    case 3->priority=Constants.TEXTBUNDLE.getString("high");
                }
                return priority;
            //TODO: HACER QUE MUESTRE TOGGLE BUTTON
            //https://tips4java.wordpress.com/2009/07/12/table-button-column/
            //https://stackoverflow.com/questions/21845768/how-to-set-toggle-button-text-value-from-db-on-jtable
            //https://stackoverflow.com/questions/7350445/how-do-i-get-the-cellrow-when-there-is-an-itemevent-in-the-jcombobox-within-the/7356518#7356518
            case 3:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TABLEDATEFORMAT);
                return simpleDateFormat.format(task.getDate());
            case 4:
                /*btnDone=new JToggleButton(Constants.TEXTBUNDLE.getString("no"));
                btnDone.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnDone.setText(Constants.TEXTBUNDLE.getString("yes"));
                        Task task = tasklist.get(rowIndex);
                        task.setDone(true);
                    }
                });
                if (task.isDone()) btnDone.isSelected();
                return btnDone;*/
                return task.isDone();
        }
        return null;
    }
//only used for the checkbox in done column
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Task Task = tasklist.get(rowIndex);
        Task.setDone((Boolean)aValue);
        /*switch (columnIndex){
            case 0: Task.setName((String) aValue);break;
            case 1: Task.setType((String) aValue);break;
            case 2: Task.setPriority((Integer) aValue);break;
            case 3: ;break;
            case 4: Task.setDone((Boolean)aValue);break;
        }*/

        this.fireTableCellUpdated(rowIndex,columnIndex);
        this.fireTableRowsUpdated(rowIndex,rowIndex);
    }
//this will show the cell for DONE? as a checkbox :)
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex==4) return Boolean.class;
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //TODO: toquetear esto para poner los componentes custom
//        Task task = getTask(rowIndex);
        if (columnIndex==4) return true;
        new TaskGUI(this, rowIndex).setVisible(true);
        return false;
    }

    private Task getTask(int rowIndex) {
        Task task = null;
        task=this.gettasklist().get(rowIndex);
        return task;
    }

    public ArrayList<Task> gettasklist() {
        return tasklist;
    }
}
