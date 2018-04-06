package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Drink;
import nl.yacht.molvenov3.repository.CrudDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editDrinks")
public class DrinkController {

    @Autowired
    private CrudDrinkRepository crudDrinkRepository;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public Drink create(@RequestBody Drink drink) {
        return crudDrinkRepository.save(drink);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Drink update(@RequestBody Drink drink) {
        return crudDrinkRepository.save(drink);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Drink> getAll() {
        return crudDrinkRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Drink findById(@PathVariable long id) {
        return this.crudDrinkRepository.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        this.crudDrinkRepository.delete(id);
    }

}
