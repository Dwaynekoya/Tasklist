package model;

import java.util.Date;
/*
* task that repeats over time
* */

public class Habit extends Task{
    private int repeatEveryX;

    public Habit(String name, String details, int priority, String type, Date date, int repeatEveryX) {
        super(name, details, priority, type, date);
        this.repeatEveryX=repeatEveryX;
    }

    public int getRepeatEveryX() {
        return repeatEveryX;
    }

    public void setRepeatEveryX(int repeatEveryX) {
        this.repeatEveryX = repeatEveryX;
    }
}
