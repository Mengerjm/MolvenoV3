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

    //Create Order door een tafelnummer in te voeren. Hier wordt automatisch de tijd en datum aan toegevoegd
    @PostMapping(value = "/createorder")
    public Order save(@RequestBody Order order){
        return this.orderRepository.save(order);
    }

    //region Get Order

    //Get all drinks of a specific order with guestID
    @GetMapping(value = "/getdrink/{id}")
    public Iterable<Drink> findAllDrinksById(@PathVariable("id") long guestID){
        return this.orderRepository.findAllDrinks(guestID);
    }

    //Get all dishes of a specific order with guestID
    @GetMapping(value = "/getdish/{id}")
    public Iterable<Dish> findAllDishesById(@PathVariable("id") long guestID){
        return this.orderRepository.findAllDishes(guestID);
    }

    //Get all specials of a specific order with guestID
    @GetMapping(value = "/getspecial/{id}")
    public Iterable<Special> findAllSpecialsById(@PathVariable("id") long guestID){
        return this.orderRepository.findAllSpecials(guestID);
    }

    //endregion

    //region Put Order

    @PostMapping(value = "/adddrink/{id}")
    public Drink addDrink(@PathVariable("id") long guestID, @RequestBody Drink drinkToAdd){
        return this.orderRepository.addDrink(guestID, drinkToAdd);
    }

    @PostMapping(value = "/adddish/{id}")
    public Dish addDish(@PathVariable("id") long guestID, @RequestBody Dish dishToAdd){
        return this.orderRepository.addDish(guestID, dishToAdd);
    }

    @PostMapping(value = "/addspecial/{id}")
    public Special addSpecial(@PathVariable("id") long guestID, @RequestBody Special specialToAdd){
        return this.orderRepository.addSpecial(guestID, specialToAdd);
    }

    @PostMapping(value = "/removedrink/{id}")
    public Drink removeDrink(@PathVariable("id") long guestID, @RequestBody Drink drinkToRemove){
        return this.orderRepository.removeDrink(guestID, drinkToRemove);
    }

    @PostMapping(value = "/removedish/{id}")
    public Dish removeDish(@PathVariable("id") long guestID, @RequestBody Dish dishToRemove){
        return this.orderRepository.removeDish(guestID, dishToRemove);
    }

    @PostMapping(value = "/removespecial/{id}")
    public Special removeSpecial(@PathVariable("id") long guestID, @RequestBody Special specialToRemove){
        return this.orderRepository.removeSpecial(guestID, specialToRemove);
    }

    //endregion

    //Delete an Order from the repository
    @DeleteMapping(value="/deleteorder/{id}")
    public void delete(@PathVariable("id") long guestID){
        this.orderRepository.delete(guestID);
    }

}

