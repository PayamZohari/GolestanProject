/**
 * Represents time of classes
 *
 * @author Payam Zohari
 * this has created to model the class times (on which day class occurs) and in what time
 */
package ir.ac.kntu;

import java.util.ArrayList;

/**
 * fields are the day of class ( not weekends) there is limitation because day is modelled by enum
 * also there is hour and minute (hour couldnt be negetive or bigger than 18 (too late!!)
 */
public class Time {
    //fields
    private WeekDays weekDays = WeekDays.FRIDAY;
    private int hour = 12;
    private int minute = 30;
    //constructor

    public Time(String w, int h, int m) {
        this.weekDays = WeekDays.valueOf(w);
        if (h >= 6 && h <= 18) {
            this.hour = h;
        }
        if (m >= 0 && m < 60) {
            this.minute = m;
        }
    }

    public Time(WeekDays weekDay, int h, int m) {
        this.weekDays = weekDay;
        if (h >= 6 && h <= 18) {
            this.hour = h;
        }
        if (m >= 0 && m < 60) {
            this.minute = m;
        }
    }
//getters

    public WeekDays getWeekDays() {
        return weekDays;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    //setters


    public void setWeekDays(WeekDays weekDays) {
        this.weekDays = weekDays;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return this.weekDays + " " + this.hour + ":" + this.minute;
    }

    /**
     * check if two time object has intercurrency(tadakhol zamani) or not which means they have less than 2hour
     * time distance (class times length are at least 2hours)
     *
     * @param other is another time oject
     * @return true if they have intercurrency and false if they dont
     */
    public boolean checkIntercurrent(Time other) {
        if (this.weekDays == other.weekDays && ((this.hour - other.hour) < 2)) {
            return true;
        } else {
            return false;
        }
    }
}
