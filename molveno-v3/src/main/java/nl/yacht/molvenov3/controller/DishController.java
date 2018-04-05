package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.repository.CrudDishRepository;
import nl.yacht.molvenov3.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editDishes")
public class DishController {

    @Autowired
    private CrudDishRepository crudDishRepository;
   // private DishRepository dishRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Dish> findAll() {
        Iterable<Dish> dishes = this.crudDishRepository.findAll();
        return dishes;
    }

    @GetMapping(value = "{id}")
    public Dish findById(@PathVariable long id) {
        return this.crudDishRepository.findOne(id);
    }
}
