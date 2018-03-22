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

    //Get all drinks of a specific order with tablenumber as "id"
    @GetMapping(value = "/getdrink/{id}")
    public Iterable<Drink> findAllDrinksById(@PathVariable("id") int tableNumber){
        return this.orderRepository.findAllDrinks(tableNumber);
    }

    //Get all dishes of a specific order with tablenumber as "id"
    @GetMapping(value = "/getdish/{id}")
    public Iterable<Dish> findAllDishesById(@PathVariable("id") int tableNumber){
        return this.orderRepository.findAllDishes(tableNumber);
    }

    //Get all specials of a specific order with tablenumber as "id"
    @GetMapping(value = "/getspecial/{id}")
    public Iterable<Special> findAllSpecialsById(@PathVariable("id") int tableNumber){
        return this.orderRepository.findAllSpecials(tableNumber);
    }

    //endregion

    //region Put Order

    @PostMapping(value = "/adddrink/{id}")
    public Drink addDrink(@PathVariable("id") int tableNumber, @RequestBody Drink drinkToAdd){
        return this.orderRepository.addDrink(tableNumber, drinkToAdd);
    }

    @PostMapping(value = "/adddish/{id}")
    public Dish addDish(@PathVariable("id") int tableNumber, @RequestBody Dish dishToAdd){
        return this.orderRepository.addDish(tableNumber, dishToAdd);
    }

    @PostMapping(value = "/addspecial/{id}")
    public Special addSpecial(@PathVariable("id") int tableNumber, @RequestBody Special specialToAdd){
        return this.orderRepository.addSpecial(tableNumber, specialToAdd);
    }

    @PostMapping(value = "/removedrink/{id}")
    public Drink removeDrink(@PathVariable("id") int tableNumber, @RequestBody Drink drinkToRemove){
        return this.orderRepository.removeDrink(tableNumber, drinkToRemove);
    }

    @PostMapping(value = "/removedish/{id}")
    public Dish removeDish(@PathVariable("id") int tableNumber, @RequestBody Dish dishToRemove){
        return this.orderRepository.removeDish(tableNumber, dishToRemove);
    }

    @PostMapping(value = "/removespecial/{id}")
    public Special removeSpecial(@PathVariable("id") int tableNumber, @RequestBody Special specialToRemove){
        return this.orderRepository.removeSpecial(tableNumber, specialToRemove);
    }

    //endregion

    //Delete an Order from the repository
    @DeleteMapping(value="/deleteorder/{id}")
    public void delete(@PathVariable("id") int tableNumber){
        this.orderRepository.delete(tableNumber);
    }

}

