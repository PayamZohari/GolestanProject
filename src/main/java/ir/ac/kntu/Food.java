/**
 * represents any food/drinks or eatable things which are main components of food reservation system
 * @author Payam Zohari
 */
package ir.ac.kntu;

public class Food {
    private String name;
    private double price;

    //constructor
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }
    //getters

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }
}
