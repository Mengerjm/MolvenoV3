package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Drink;
import nl.yacht.molvenov3.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    /*
    @RequestMapping(value="/orderlist/{id}", method= RequestMethod.GET)
    public Order get(@PathVariable("id") int tableNumber) {
        for (Order correctOrder : Restaurant.getOrderList()) {
            if (correctOrder.getTableNumber() == tableNumber) {
                return correctOrder;
            }
        }
        return null;
    }

    @RequestMapping(value= "/drink/{id}", method= RequestMethod.PUT)
    public void addDrink(@PathVariable("id") int tableNumber,@RequestBody Drink drink){
        for (Order correctOrder : Restaurant.getOrderList()) {
            if (correctOrder.getTableNumber() == tableNumber) {
               correctOrder.getDrinks().add(drink);
            }
        }
    }
    */
}

