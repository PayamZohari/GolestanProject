/**
 * Presents lunch including main food , salad and drinks
 * this is a child class to Meal
 * @author Payam Zohari
 */


package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * based on weekday the menu changes and gives you 3 options for each part and main food options has one new food
 * every day in week (cool rotation system!!)
 */
public class Lunch extends Meal {
    private static ArrayList<Food> lunchMainFood = new ArrayList<>();

    {
        Food f1 = new Food("pizza", 15000.0);
        lunchMainFood.add(f1);
        Food f2 = new Food("kabab", 20000.0);
        lunchMainFood.add(f2);
        Food f3 = new Food("khorak morgh", 5000.0);
        lunchMainFood.add(f3);
        Food f4 = new Food("joojeh kabab", 8000.0);
        lunchMainFood.add(f4);
        Food f5 = new Food("khorak mahi", 10000.0);
        lunchMainFood.add(f5);
        Food f6 = new Food("khoresh gheimeh", 13000.0);
        lunchMainFood.add(f6);
        Food f7 = new Food("khoresh ghormeh sabzi", 18000.0);
        lunchMainFood.add(f7);
    }

    private static Food[] lunchMainFoodMenu = new Food[3];
    private static Food[] lunchSaladsMenu = new Food[3];

    {
        Food s1 = new Food("salad cesar", 4000.0);
        lunchSaladsMenu[0] = s1;
        Food s2 = new Food("salad chahar fasl", 3000.0);
        lunchSaladsMenu[1] = s2;
        Food s3 = new Food("salad shirazi", 2000.0);
        lunchSaladsMenu[2] = s3;
    }

    private static Food[] lunchDrinksMenu = new Food[3];

    {
        Food d1 = new Food("ab madani", 500.0);
        lunchDrinksMenu[0] = d1;
        Food d2 = new Food("dough", 1500.0);
        lunchDrinksMenu[1] = d2;
        Food d3 = new Food("nooshabeh", 1500.0);
        lunchDrinksMenu[2] = d3;
    }

    private Food lunchFood;
    private Food lunchSalad;
    private Food lunchDrink;

    //constructor
    public Lunch(WeekDays weekDay, Scanner input) {
        super("lunch", weekDay, 0.0);
        int chooseMainFoodMenu = weekDay.getDayNumber();
        int j = 0;
        for (int i = chooseMainFoodMenu; i < chooseMainFoodMenu + 3; i++) {
            lunchMainFoodMenu[j] = lunchMainFood.get(i % 7);
            j++;
        }
        System.out.println("choose your main food");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + "-" + lunchMainFoodMenu[i].getName());
        }
        int chooseMainFood = Integer.parseInt(input.nextLine());
        chooseMainFood--;
        this.lunchFood = lunchMainFoodMenu[chooseMainFood];

        System.out.println("choose your salad");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + "-" + lunchSaladsMenu[i].getName());
        }
        int chooseSalad = Integer.parseInt(input.nextLine());
        chooseSalad--;
        this.lunchSalad = lunchSaladsMenu[chooseSalad];

        System.out.println("choose your drinks");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + "-" + lunchDrinksMenu[i].getName());
        }
        int chooseDrinks = Integer.parseInt(input.nextLine());
        chooseDrinks--;
        this.lunchDrink = lunchDrinksMenu[chooseDrinks];

        this.setPrice(this.getPrice() + this.lunchFood.getPrice() + this.lunchSalad.getPrice() + this.lunchDrink.getPrice());
    }
    //getters

    public static ArrayList<Food> getLunchMainFood() {
        return lunchMainFood;
    }

    public static Food[] getLunchMainFoodMenu() {
        return lunchMainFoodMenu;
    }

    public static Food[] getLunchSaladsMenu() {
        return lunchSaladsMenu;
    }

    public static Food[] getLunchDrinksMenu() {
        return lunchDrinksMenu;
    }

    public Food getLunchFood() {
        return lunchFood;
    }

    public Food getLunchSalad() {
        return lunchSalad;
    }

    public Food getLunchDrink() {
        return lunchDrink;
    }
    //setters

    public static void setLunchMainFood(ArrayList<Food> lunchMainFood) {
        Lunch.lunchMainFood = lunchMainFood;
    }

    public static void setLunchMainFoodMenu(Food[] lunchMainFoodMenu) {
        Lunch.lunchMainFoodMenu = lunchMainFoodMenu;
    }

    public static void setLunchSaladsMenu(Food[] lunchSaladsMenu) {
        Lunch.lunchSaladsMenu = lunchSaladsMenu;
    }

    public static void setLunchDrinksMenu(Food[] lunchDrinksMenu) {
        Lunch.lunchDrinksMenu = lunchDrinksMenu;
    }

    public void setLunchFood(Food lunchFood) {
        this.lunchFood = lunchFood;
    }

    public void setLunchSalad(Food lunchSalad) {
        this.lunchSalad = lunchSalad;
    }

    public void setLunchDrink(Food lunchDrink) {
        this.lunchDrink = lunchDrink;
    }


    @Override
    public String toString() {
        return "Food{" +
                "/meal: " + this.getFoodType() +
                "/main food : " + this.lunchFood +
                "/salad : " + this.lunchSalad +
                "/drink : " + this.lunchDrink +
                "/total price : " + this.getPrice() + "$" +
                '}';
    }
}
