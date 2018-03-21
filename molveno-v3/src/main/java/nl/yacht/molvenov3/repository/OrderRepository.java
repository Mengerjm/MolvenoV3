package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.model.Drink;
import nl.yacht.molvenov3.model.Order;
import nl.yacht.molvenov3.model.Special;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private Map<Integer, Order> orderMap = new HashMap<>();

    public Order save(Order order){
        int tableNumber = order.getTableNumber();
        this.orderMap.put(tableNumber, order);
        return order;
    }

    public List<Drink> findAllDrinks(int tableNumber) {
        Order myOrder = this.orderMap.get(tableNumber);
        return myOrder.getDrinks();
    }

    public List<Dish> findAllDishes(int tableNumber) {
        Order myOrder = this.orderMap.get(tableNumber);
        return myOrder.getDishes();
    }

    public List<Special> findAllSpecials(int tableNumber) {
        Order myOrder = this.orderMap.get(tableNumber);
        return myOrder.getSpecials();
    }

    public Drink addDrink(int tableNumber, Drink drinkToAdd){
        Order myOrder = this.orderMap.get(tableNumber);
        myOrder.getDrinks().add(drinkToAdd);
        return drinkToAdd;
    }

    public Dish addDish(int tableNumber, Dish dishToAdd) {
        Order myOrder = this.orderMap.get(tableNumber);
        myOrder.getDishes().add(dishToAdd);
        return dishToAdd;
    }

    public Special addSpecial(int tableNumber, Special specialToAdd) {
        Order myOrder = this.orderMap.get(tableNumber);
        myOrder.getSpecials().add(specialToAdd);
        return specialToAdd;
    }

    public void delete(int tableNumber){
        this.orderMap.remove(tableNumber);
    }

}
