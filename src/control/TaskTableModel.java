package control;

import model.Task;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TaskTableModel extends AbstractTableModel {
    private ArrayList<Task> tasklist;
    private String[] columnas;

    public TaskTableModel() {
        this.tasklist=new ArrayList<>();
        columnas=Constants.DEFAULLT_COLUMNS;
    }

    public TaskTableModel(ArrayList<Task> Tasks) {
        this.tasklist = Tasks;
        columnas=Constants.DEFAULLT_COLUMNS;
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
        return columnas.length;
    }
    //necesario para mostrar columnas
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task Task = tasklist.get(rowIndex);
        switch (columnIndex){
            case 0: return Task.getName();
            case 1: return Task.getType();
            case 2: return Task.getPriority();
            //TODO: HACER QUE MUESTRE TOGGLE BUTTON
            case 3: return Task.getName();
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
            case 3: Task.setDone((Boolean) aValue);break;
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
