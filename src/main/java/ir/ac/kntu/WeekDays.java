/**
 * Represents days of week
 *
 * @author Payam Zohari
 * the days of week which university is open
 */

package ir.ac.kntu;

public enum WeekDays {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5);
    private int dayNumber;

    private WeekDays(int d) {
        this.dayNumber = d;
    }

    //getter
    public int getDayNumber() {
        return dayNumber;
    }
}
