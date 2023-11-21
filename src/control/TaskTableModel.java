package control;

import model.Task;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
            this.fireTableRowsInserted(tasklist.size(), tasklist.size()+1);
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
            case 2: return task.getPriority();
            //TODO: HACER QUE MUESTRE TOGGLE BUTTON
            case 3:
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
