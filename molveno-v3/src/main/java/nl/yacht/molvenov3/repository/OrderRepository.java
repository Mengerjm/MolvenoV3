package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.model.Drink;
import nl.yacht.molvenov3.model.Order;
import nl.yacht.molvenov3.model.Special;
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

    //endregion

    //region Put Order

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

    //Moet equals methode aanpassen om te vergelijken op field name
    public Drink removeDrink(int tableNumber, Drink drinkToRemove){
        Order myOrder = this.orderMap.get(tableNumber);
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
    public Dish removeDish(int tableNumber, Dish dishToRemove){
        Order myOrder = this.orderMap.get(tableNumber);
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
    public Special removeSpecial(int tableNumber, Special specialToRemove){
        Order myOrder = this.orderMap.get(tableNumber);
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

    public void delete(int tableNumber){
        this.orderMap.remove(tableNumber);
    }

}
