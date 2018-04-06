package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Ingredient;
import nl.yacht.molvenov3.repository.CrudIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

   @Autowired
    private CrudIngredientRepository crudIngredientRepository;



    @RequestMapping(value = "", method = RequestMethod.POST)
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return crudIngredientRepository.save(ingredient);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Ingredient update(@RequestBody Ingredient ingredient) {
        return crudIngredientRepository.save(ingredient);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Ingredient> getAll() {
        return crudIngredientRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Ingredient findById(@PathVariable long id) {
        return this.crudIngredientRepository.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        this.crudIngredientRepository.delete(id);
    }
}
