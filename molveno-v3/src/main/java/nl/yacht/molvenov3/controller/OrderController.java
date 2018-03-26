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

    //Create Order
    @PostMapping(value = "/createorder")
    public Order save(@RequestBody Order order){
        return this.orderRepository.save(order);
    }

    @GetMapping(value = "/getorders")
    public Iterable<Order> getAll(){
        return this.orderRepository.findAll();
    }

    //region Get Order

    //Get all drinks of a specific order with guestID
    @GetMapping(value = "/getdrink/{id}")
    public Iterable<Drink> findAllDrinksById(@PathVariable("id") long orderId){
        return this.orderRepository.findAllDrinks(orderId);
    }

    //Get all dishes of a specific order with guestID
    @GetMapping(value = "/getdish/{id}")
    public Iterable<Dish> findAllDishesById(@PathVariable("id") long orderId){
        return this.orderRepository.findAllDishes(orderId);
    }

    //Get all specials of a specific order with guestID
    @GetMapping(value = "/getspecial/{id}")
    public Iterable<Special> findAllSpecialsById(@PathVariable("id") long orderId){
        return this.orderRepository.findAllSpecials(orderId);
    }

    //endregion

    //region Put Order

    @PutMapping(value = "/adddrink/{id}")
    public Drink addDrink(@PathVariable("id") long orderId, @RequestBody Drink drinkToAdd){
        return this.orderRepository.addDrink(orderId, drinkToAdd);
    }

    @PutMapping(value = "/adddish/{id}")
    public Dish addDish(@PathVariable("id") long orderId, @RequestBody Dish dishToAdd){
        return this.orderRepository.addDish(orderId, dishToAdd);
    }

    @PutMapping(value = "/addspecial/{id}")
    public Special addSpecial(@PathVariable("id") long orderId, @RequestBody Special specialToAdd){
        return this.orderRepository.addSpecial(orderId, specialToAdd);
    }

    @PutMapping(value = "/removedrink/{id}")
    public Drink removeDrink(@PathVariable("id") long orderId, @RequestBody Drink drinkToRemove){
        return this.orderRepository.removeDrink(orderId, drinkToRemove);
    }

    @PutMapping(value = "/removedish/{id}")
    public Dish removeDish(@PathVariable("id") long orderId, @RequestBody Dish dishToRemove){
        return this.orderRepository.removeDish(orderId, dishToRemove);
    }

    @PutMapping(value = "/removespecial/{id}")
    public Special removeSpecial(@PathVariable("id") long orderId, @RequestBody Special specialToRemove){
        return this.orderRepository.removeSpecial(orderId, specialToRemove);
    }

    //endregion

    //Delete an Order from the repository
    @DeleteMapping(value="/deleteorder/{id}")
    public void delete(@PathVariable("id") long orderId){
        this.orderRepository.delete(orderId);
    }

}

