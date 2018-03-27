package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private static long counter = 0;
    private Map<Long, Order> orderMap = new HashMap<>();

    public Order save(Order order){
        counter++;
        this.orderMap.put(counter, order);
        order.setOrderId(counter);
        return order;
    }

    public Iterable<Order> findAll(){
        return this.orderMap.values();
    }

    //region Get Order

    public List<Drink> findAllDrinks(long orderId) {
        Order myOrder = this.orderMap.get(orderId);
        return myOrder.getDrinks();
    }

    public List<Dish> findAllDishes(long orderId) {
        Order myOrder = this.orderMap.get(orderId);
        return myOrder.getDishes();
    }

    public List<Special> findAllSpecials(long orderId) {
        Order myOrder = this.orderMap.get(orderId);
        return myOrder.getSpecials();
    }

    //endregion

    //region Put Order

    public Drink addDrink(long orderId, Drink drinkToAdd){
        Order myOrder = this.orderMap.get(orderId);
        myOrder.getDrinks().add(drinkToAdd);
        return drinkToAdd;
    }

    public Dish addDish(long orderId, Dish dishToAdd) {
        Order myOrder = this.orderMap.get(orderId);
        myOrder.getDishes().add(dishToAdd);
        return dishToAdd;
    }

    public Special addSpecial(long orderId, Special specialToAdd) {
        Order myOrder = this.orderMap.get(orderId);
        myOrder.getSpecials().add(specialToAdd);
        return specialToAdd;
    }

    public Drink removeDrink(long orderId, Drink drinkToRemove){
        Order myOrder = this.orderMap.get(orderId);
        int index = 0;
        for (Drink drink : myOrder.getDrinks()) {
            if (drinkToRemove.equals(drink)) {
                myOrder.getDrinks().remove(index);
                return myOrder.getDrinks().get(index);
            }
            index++;
        }
        return null;
    }

    //Moet equals methode aanpassen om te vergelijken op field name
    public Dish removeDish(long orderId, Dish dishToRemove){
        Order myOrder = this.orderMap.get(orderId);
        int index = 0;
        for (Dish dish : myOrder.getDishes()) {
            if (dishToRemove.equals(dish)) {
                myOrder.getDishes().remove(index);
                return myOrder.getDishes().get(index);
            }
            index++;
        }
        return null;
    }

    //Moet equals methode aanpassen om te vergelijken op field name
    public Special removeSpecial(long orderId, Special specialToRemove){
        Order myOrder = this.orderMap.get(orderId);
        int index = 0;
        for (Special special: myOrder.getSpecials()) {
            if (specialToRemove.equals(special)) {
                myOrder.getSpecials().remove(index);
                return myOrder.getSpecials().get(index);
            }
            index++;
        }
        return null;
    }

    //endregion

    public void delete(long orderId){
        this.orderMap.remove(orderId);
    }

}
