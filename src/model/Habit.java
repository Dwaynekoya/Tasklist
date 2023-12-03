package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

    public Habit(Habit oldHabit) {
        super(oldHabit.getName(), oldHabit.getDetails(), oldHabit.getPriority(), oldHabit.getType(), oldHabit.getDate());
        this.repeatEveryX=oldHabit.getRepeatEveryX();
        LocalDate oldDate = oldHabit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date newDate = Date.from(oldDate.plusDays(this.repeatEveryX).atStartOfDay().toInstant(ZoneOffset.UTC));
        this.setDate(newDate);
    }

    public int getRepeatEveryX() {
        return repeatEveryX;
    }

    public void setRepeatEveryX(int repeatEveryX) {
        this.repeatEveryX = repeatEveryX;
    }
}
