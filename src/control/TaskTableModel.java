package control;

import model.Task;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                return task.getPriority();
            //TODO: HACER QUE MUESTRE TOGGLE BUTTON
            //https://tips4java.wordpress.com/2009/07/12/table-button-column/
            //https://stackoverflow.com/questions/21845768/how-to-set-toggle-button-text-value-from-db-on-jtable
            //https://stackoverflow.com/questions/7350445/how-do-i-get-the-cellrow-when-there-is-an-itemevent-in-the-jcombobox-within-the/7356518#7356518
            case 3:
                btnDone=new JToggleButton(Constants.TEXTBUNDLE.getString("no"));
                btnDone.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnDone.setText(Constants.TEXTBUNDLE.getString("yes"));
                        Task task = tasklist.get(rowIndex);
                        task.setDone(true);
                    }
                });
                if (task.isDone()) btnDone.isSelected();
                return btnDone;
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Task Task = tasklist.get(rowIndex);
        switch (columnIndex){
            case 0: Task.setName((String) aValue);break;
            //TODO : combobox?
            case 1: Task.setType((String) aValue);break;
            //TODO: spinner?
            case 2: Task.setPriority((Integer) aValue);break;
            //TODO: togglebutton?
            case 3: ;break;
        }

        this.fireTableCellUpdated(rowIndex,columnIndex);
        this.fireTableRowsUpdated(rowIndex,rowIndex);
    }
    //para permitir editar celdas

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //TODO: toquetear esto para poner los componentes custom
        return true;
    }

    public ArrayList<Task> gettasklist() {
        return tasklist;
    }
}
