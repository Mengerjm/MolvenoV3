package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {

    private Map<Integer, Order> orderMap = new HashMap<>();

    public Order saveOrder(Order myOrder, int tableNumber){
        this.orderMap.put(tableNumber, myOrder);
        myOrder.setTableNumber(tableNumber);
        return myOrder;
    }

}
