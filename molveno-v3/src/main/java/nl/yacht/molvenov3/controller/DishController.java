package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.repository.CrudDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editDishes")
public class DishController {

    @Autowired
    private CrudDishRepository crudDishRepository;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public Dish creat(@RequestBody Dish dish) {
        return crudDishRepository.save(dish);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Dish update(@RequestBody Dish dish) {
        return crudDishRepository.save(dish);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Dish> getAll() {
        return crudDishRepository.findAll();
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Dish findById(@PathVariable long id) {
        return this.crudDishRepository.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        this.crudDishRepository.delete(id);
    }
}
