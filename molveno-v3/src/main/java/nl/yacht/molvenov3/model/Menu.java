package nl.yacht.molvenov3.model;

import java.io.Serializable;
import java.util.List;

public class Menu  implements Serializable {
    private List<Drink> drinks;
    private List<Dish> dishes;
    private List<Special> specials;
    private Order order;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Special> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu(){

    }

    public Menu(List<Drink> drinks, List<Dish> dishes, List<Special> specials, Order order) {
        this.drinks = drinks;
        this.dishes = dishes;
        this.specials = specials;
        this.order = order;
    }
}
