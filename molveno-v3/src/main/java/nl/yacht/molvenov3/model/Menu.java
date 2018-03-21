package nl.yacht.molvenov3.model;

import java.util.List;

public class Menu {

    private List<Drink> drinks;

    private List<Dish> dishes;

    private List<Special> specials;

    public Menu(){

    }

    public Menu(List<Drink> drinks, List<Dish> dishes, List<Special> specials) {
        this.drinks = drinks;
        this.dishes = dishes;
        this.specials = specials;
    }

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

}
