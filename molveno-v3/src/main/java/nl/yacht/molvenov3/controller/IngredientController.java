package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {

    @GetMapping("/api/getIngrient")
    public Ingredient getIngrient(){

        Ingredient result = new Ingredient("pasta", true, 100, 3.40);

        return result;
    }

    @PostMapping
    public void save(@RequestBody Ingredient ingredient){
        System.err.println(ingredient.isAllergen());
        System.err.println(ingredient.getNumberOfStock());
        System.err.println(ingredient.getCostPrice());
    }



}
