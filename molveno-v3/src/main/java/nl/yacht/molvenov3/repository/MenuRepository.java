package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MenuRepository {

    private static long counter = 0;

    private Map<Long, Drink> drinks = new HashMap<>();
    private Map<Long, Dish> dishes = new HashMap<>();
    private Map<Long, Special> specials = new HashMap<>();
    private Map<Long, Order> orders = new HashMap<>();

    //region findAll
    public Iterable<Drink> findAllDrinks() {
        Iterable<Drink> result = this.drinks.values();
        return result;
    }

    public Iterable<Dish> findAllDishes() {
        Iterable<Dish> result = this.dishes.values();
        return result;
    }

    public Iterable<Special> findAllSpecials() {
        Iterable<Special> result = this.specials.values();
        return result;
    }

    public Iterable<Order> findAllOrders() {
        Iterable<Order> result = this.orders.values();
        return result;
    }
    //endregion

    //region save

    public Drink save(Drink drinkToSave) {
        counter++;
        drinkToSave.setId(counter);
        this.drinks.put(counter, drinkToSave);

        return this.drinks.get(counter);
    }

    public Dish save(Dish dishToSave) {
        counter++;
        dishToSave.setId(counter);
        this.dishes.put(counter, dishToSave);

        return this.dishes.get(counter);
    }

    public Special save(Special specialToSave) {
        counter++;
        specialToSave.setId(counter);
        this.specials.put(counter, specialToSave);

        return this.specials.get(counter);
    }

    public Order save(Order orderToSave) {
        counter++;
        orderToSave.setId(counter);
        this.orders.put(counter, orderToSave);

        return this.orders.get(counter);
    }
    //endregion

    //todo Update statements


}
