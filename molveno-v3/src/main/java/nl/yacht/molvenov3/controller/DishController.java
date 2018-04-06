package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.repository.CrudDishRepository;
import nl.yacht.molvenov3.util.DishUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editDishes")
public class DishController {

    @Autowired
    private CrudDishRepository crudDishRepository;


    @RequestMapping(value = "/newDish", method = RequestMethod.POST)
    public Dish create(@RequestBody Dish dish) {
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
        return crudDishRepository.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        crudDishRepository.delete(id);
    }

    @GetMapping(value = "/findall")
    public Iterable<Dish> findAll() {
        return crudDishRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public Dish findById(@PathVariable("id") Long id) {
        return crudDishRepository.findOne(id);
    }

    @PutMapping(value = "/update/{id}")
    public Dish update(@PathVariable("id") Long id, @RequestBody Dish input) {
        return crudDishRepository.save(DishUtil.update(crudDishRepository.findOne(id), input));
    }
}
