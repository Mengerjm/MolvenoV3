package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.*;
import org.springframework.stereotype.Repository;

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

    //region Get Order

    public List<Drink> findAllDrinks(long guestID) {
        Order myOrder = this.orderMap.get(guestID);
        return myOrder.getDrinks();
    }

    public List<Dish> findAllDishes(long guestID) {
        Order myOrder = this.orderMap.get(guestID);
        return myOrder.getDishes();
    }

    public List<Special> findAllSpecials(long guestID) {
        Order myOrder = this.orderMap.get(guestID);
        return myOrder.getSpecials();
    }

    //endregion

    //region Put Order

    public Drink addDrink(long guestID, Drink drinkToAdd){
        Order myOrder = this.orderMap.get(guestID);
        myOrder.getDrinks().add(drinkToAdd);
        return drinkToAdd;
    }

    public Dish addDish(long guestID, Dish dishToAdd) {
        Order myOrder = this.orderMap.get(guestID);
        myOrder.getDishes().add(dishToAdd);
        return dishToAdd;
    }

    public Special addSpecial(long guestID, Special specialToAdd) {
        Order myOrder = this.orderMap.get(guestID);
        myOrder.getSpecials().add(specialToAdd);
        return specialToAdd;
    }

    public Drink removeDrink(long guestID, Drink drinkToRemove){
        Order myOrder = this.orderMap.get(guestID);
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
    public Dish removeDish(long guestID, Dish dishToRemove){
        Order myOrder = this.orderMap.get(guestID);
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
    public Special removeSpecial(long guestID, Special specialToRemove){
        Order myOrder = this.orderMap.get(guestID);
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

    public void delete(long guestID){
        this.orderMap.remove(guestID);
    }

}
