package model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String name;
    private String details;
    private int priority;
    private int repeatEveryX;
    private String type;
    private boolean habit;
    private boolean done;
    private Date date;

    public Task(String name, String details, int priority, int repeatEveryX, String type, boolean habit, Date date) {
        this.name = name;
        this.details = details;
        this.priority = priority;
        this.repeatEveryX = repeatEveryX;
        this.type = type;
        this.habit = habit;
        //por defecto una tarea  nueva no ha sido realizada:
        this.done=false;
        this.date=date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getRepeatEveryX() {
        return repeatEveryX;
    }

    public void setRepeatEveryX(int repeatEveryX) {
        this.repeatEveryX = repeatEveryX;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHabit() {
        return habit;
    }

    public void setHabit(boolean habit) {
        this.habit = habit;
    }
}
