/**
 * represents every meal reservation from studnents
 *
 * @author Pyam Zoahri
 */

package ir.ac.kntu;

import java.util.Objects;

/**
 * this class has meal refrence field (composition) and the location and time of recienving it (composition and
 * code reuse)!!
 */
public class MealOrder {
    private Meal meal;
    private Student student;
    private Time recievingTime;
    private Location recievingLocation;

    //constructor
    public MealOrder(Student student, Meal meal, Time recievingTime, Location recievingLocation) {
        this.student = student;
        this.meal = meal;
        this.recievingTime = recievingTime;
        this.recievingLocation = recievingLocation;

        boolean isUnique = true;
        if (DataBase.getGolestanDataBase().getMealOrders() != null &&
                DataBase.getGolestanDataBase().getMealOrders().size() != 0) {
            for (int i = 0; i < DataBase.getGolestanDataBase().getMealOrders().size(); i++) {
                if (this.equals(DataBase.getGolestanDataBase().getMealOrders().get(i))) {
                    isUnique = false;
                    break;
                }
            }
        }
        if (isUnique) {
            DataBase.getGolestanDataBase().getMealOrders().add(this);
            student.getStudentMealOrders().add(this);
        } else {
            System.out.println("sorry you had a reservation for this day , this week and this meal");
        }
    }

    //getters
    public Meal getMeal() {
        return meal;
    }

    public Student getStudent() {
        return student;
    }

    public Time getRecievingTime() {
        return recievingTime;
    }

    public Location getRecievingLocation() {
        return recievingLocation;
    }
    //setter

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setRecievingTime(Time recievingTime) {
        this.recievingTime = recievingTime;
    }

    public void setRecievingLocation(Location recievingLocation) {
        this.recievingLocation = recievingLocation;
    }

    public String toString() {
        return "MealOrder{" +
                "order from student : " + this.student.toString() +
                "/the meal reservation : " + this.meal.toString() +
                "/the location for recieving this meal : " + this.recievingLocation.toString() +
                "/the time for recieving this meal : " + this.recievingTime.toString() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MealOrder mealOrder = (MealOrder) o;
        return Objects.equals(meal.getWeekDay(), mealOrder.meal.getWeekDay()) &&
                Objects.equals(meal.getFoodType(), mealOrder.meal.getFoodType()) &&
                Objects.equals(student, mealOrder.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal, student, recievingTime, recievingLocation);
    }
}
