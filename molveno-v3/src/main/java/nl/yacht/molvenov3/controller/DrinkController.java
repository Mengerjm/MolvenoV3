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
    public Drink creat (@RequestBody Drink drink){
        return crudDrinkRepository.save(drink);
    }

     @RequestMapping(value = "", method = RequestMethod.GET)
     public Iterable<Drink> getAll(){
        return crudDrinkRepository.findAll();
     }


    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void delete (@PathVariable long id){
        this.crudDrinkRepository.delete(id);
    }

}
