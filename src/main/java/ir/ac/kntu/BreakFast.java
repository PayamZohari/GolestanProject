/**
 * Presents breakfast package including same components for every day
 * this is a child class to Meal
 * @author Payam Zohari
 */

package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Arrays;

public class BreakFast extends Meal {
    private Food[] breakFastPackage = new Food[4];

    {
        Food b1 = new Food("an egg", 2000.0);
        breakFastPackage[0] = b1;
        Food b2 = new Food("a mini jarhoney", 4000.0);
        breakFastPackage[1] = b2;
        Food b3 = new Food("a loaf of bread", 1000.0);
        breakFastPackage[2] = b3;
        Food b4 = new Food("a cup of tea", 500.0);
        breakFastPackage[3] = b4;
    }

    //constructor
    public BreakFast(WeekDays weekDay) {
        super("breakfast", weekDay, 0.0);
        for (int i = 0; i < breakFastPackage.length; i++) {
            this.setPrice(getPrice() + breakFastPackage[i].getPrice());
        }
    }
    //getter

    public Food[] getBreakFastPackage() {
        return breakFastPackage;
    }


    //setter

    public void setBreakFastPackage(Food[] breakFastPackage) {
        this.breakFastPackage = breakFastPackage;
    }

    public String printPackages() {
        String temp = "";
        for (int i = 0; i < breakFastPackage.length; i++) {
            temp += breakFastPackage[i].getName();
        }
        return temp;
    }

    @Override
    public String toString() {
        return "Food{" +
                "/meal: " + this.getFoodType() +
                "/package contains :" + printPackages() +
                "/total price : " + this.getPrice() + "$" +
                '}';
    }
}
