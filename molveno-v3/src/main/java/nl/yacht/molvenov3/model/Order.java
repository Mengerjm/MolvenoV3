package nl.yacht.molvenov3.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {

    private Map<String, Drink> drinks;
    private Map<String, Dish> dishes;
    private Map<String, Special> specials;
    private int tableNumber;
    private Guest guest;
    private boolean isPaid;
    private LocalDateTime orderDateTime;



    //region getters and setters

    public Map<String, Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Map<String, Drink> drinks) {
        this.drinks = drinks;
    }

    public Map<String, Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Map<String, Dish> dishes) {
        this.dishes = dishes;
    }

    public Map<String, Special> getSpecials() {
        return specials;
    }

    public void setSpecials(Map<String, Special> specials) {
        this.specials = specials;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
/**
    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
*/
    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }


    //endregion

    public Order(){}

    public Order(Map<String,Drink> drinks, Map<String, Dish> dishes, Map<String, Special> specials, int tableNumber, Guest guest) {
        this.drinks = drinks;
        this.dishes = dishes;
        this.specials = specials;
        this.tableNumber = tableNumber;
        this.guest = guest;
        this.orderDateTime = LocalDateTime.now();
        //OrderRepository.addOrderToList(this);
    }
}
