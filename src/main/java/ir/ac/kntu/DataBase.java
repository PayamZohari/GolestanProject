/**
 * this is a singleton form class which represennts the class database
 * @author Payam Zohari
 */

package ir.ac.kntu;

import java.util.ArrayList;

/**
 * this database contains just food reservation requests but it could contain all static arrays in project
 */
public class DataBase {
    private static DataBase golestanDataBase = new DataBase();
    private ArrayList<MealOrder> mealOrders;

    //constructor
    private DataBase() {
        this.mealOrders = new ArrayList<>();
    }

    //getter

    public static DataBase getGolestanDataBase() {
        return golestanDataBase;
    }

    public ArrayList<MealOrder> getMealOrders() {
        return mealOrders;
    }

    //setter
}
