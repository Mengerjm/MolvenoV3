package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/editDishes")
public class DishController {

    @Autowired
    private DishRepository dishRepository;

    @GetMapping
    public Iterable<Dish> findAll() {
        Iterable<Dish> dishes = this.dishRepository.findAll();
        return dishes;
    }

    @GetMapping(value = "{id}")
    public Dish findById(@PathVariable long id) {
        return this.dishRepository.findById(id);
    }
}
