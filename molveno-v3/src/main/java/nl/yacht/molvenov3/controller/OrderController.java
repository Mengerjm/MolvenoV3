package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.model.Drink;
import nl.yacht.molvenov3.model.Order;
import nl.yacht.molvenov3.model.Special;
import nl.yacht.molvenov3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    //region Get Order

    //Get all drinks of a specific order with tablenumber as "id"
    @GetMapping(value = "/getdrink/{id}")
    public Iterable<Drink> findAllDrinksById(@PathVariable("id") int tableNumber){
        return this.orderRepository.findAllDrink(tableNumber);
    }

    //Get all dishes of a specific order with tablenumber as "id"
    @GetMapping(value = "/getdish/{id}")
    public Iterable<Dish> findAllDishesById(@PathVariable("id") int tableNumber){
        return this.orderRepository.findAllDish(tableNumber);
    }

    //Get all specials of a specific order with tablenumber as "id"
    @GetMapping(value = "/getspecial/{id}")
    public Iterable<Special> findAllSpecialsById(@PathVariable("id") int tableNumber){
        return this.orderRepository.findAllSpecial(tableNumber);
    }

    //endregion

    @PostMapping(value = "/putdrink/{id}")
    public Drink saveDrink(@PathVariable("id") int tableNumber, @RequestBody Drink drinkToAdd){
        return this.orderRepository.addDrink(tableNumber, drinkToAdd);
    }

    //Delete an Order from the repository / checkout
    @DeleteMapping(value="{id}")
    public void delete(@PathVariable("id") int tableNumber){
        this.orderRepository.delete(tableNumber);
    }

}

