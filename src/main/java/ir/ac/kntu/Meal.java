/**represents meal including lunch and breakfast
 * @author Paym Zohari
 */
package ir.ac.kntu;

/**
 * contains the day of reservartion
 * the typpe ( breakfast or lunch)
 * and total cost
 */
public abstract class Meal {
    private WeekDays weekDay;
    private String foodType;
    private double price;

    //constructor
    public Meal(String foodType, WeekDays weekDay, double price) {
        this.weekDay = weekDay;
        this.foodType = foodType;
        this.price = price;
    }

    //getters
    public String getFoodType() {
        return foodType;
    }

    public WeekDays getWeekDay() {
        return weekDay;
    }

    public double getPrice() {
        return price;
    }

    //setters

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void setWeekDay(WeekDays weekDay) {
        this.weekDay = weekDay;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "food type=" + foodType +
                '}';
    }

}
