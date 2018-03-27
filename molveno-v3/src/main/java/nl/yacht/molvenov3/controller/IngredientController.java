package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Ingredient;
import nl.yacht.molvenov3.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {


    @Autowired
    private IngredientRepository ingredientRepository;


    @GetMapping
    public Iterable<Ingredient> getAll() {

        Iterable<Ingredient> ingredients = this.ingredientRepository.getAll();
        return ingredients;
    }

    @PostMapping
    public Ingredient save(@RequestBody Ingredient ingredient) {
        return this.ingredientRepository.save(ingredient);

    }

    @PutMapping(value = "{id}")
    public Ingredient update(@PathVariable long id, @RequestBody Ingredient input) {
        return this.ingredientRepository.update(id, input);
    }


    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id) {
        this.ingredientRepository.delete(id);
    }

    //@GetMapping
    //public Ingredient getIngredient(){

    //    Ingredient result = new Ingredient("pasta", true, 100, 3.40);

    //    return result;
    //}

    //@PostMapping
    //public void save(@RequestBody Ingredient ingredient){
    //    System.err.println(ingredient.getName());
    //    System.err.println(ingredient.isAllergen());
    //    System.err.println(ingredient.getNumberOfStock());
    //    System.err.println(ingredient.getCostPrice());
    //}



}
