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

    public List<Drink> findAllDrink(int tableNumber) {
        Order myOrder = this.orderMap.get(tableNumber);
        return myOrder.getDrinks();
    }

    public List<Dish> findAllDish(int tableNumber) {
        Order myOrder = this.orderMap.get(tableNumber);
        return myOrder.getDishes();
    }

    public List<Special> findAllSpecial(int tableNumber) {
        Order myOrder = this.orderMap.get(tableNumber);
        return myOrder.getSpecials();
    }

    public Drink addDrink(int tableNumber, Drink drinkToAdd){
        Order myOrder = this.orderMap.get(tableNumber);
        myOrder.getDrinks().add(drinkToAdd);
        this.orderMap.put(tableNumber, myOrder);
        return toBeSaved;
    }

    public void delete(int tableNumber){
        this.orderMap.remove(tableNumber);
    }

}
