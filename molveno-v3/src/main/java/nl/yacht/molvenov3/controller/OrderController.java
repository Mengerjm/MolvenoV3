package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Guest;
import nl.yacht.molvenov3.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {

    /*
     *public Order findCorrectOrder(int tableNumber, Guest g) {
     *   for (Order correctOrder : Restaurant.getOrderList()) {
     *       if (correctOrder.getTableNumber() == tableNumber && correctOrder.getGuest().getName().equalsIgnoreCase(g.getName())) {
     *           return correctOrder;
     *       }
     *   }
     *   return null;
     *}
     */

    public Order get(){
        Order myOrder =  new findCorrectOrder();
        return myOrder;
    }

}
