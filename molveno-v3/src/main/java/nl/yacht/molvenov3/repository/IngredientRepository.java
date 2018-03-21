package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IngredientRepository {

    private static long counter = 0;

    private Map<Long, Ingredient> ingredienet = new HashMap<>();


    //get all ingredients
    public Iterable<Ingredient> getAll(){
        Iterable<Ingredient> result = this.ingredienet.values();

        return result;
    }

    //save ingedient
    public Ingredient save(Ingredient ingredienetToBeSaved) {

        counter++;
        this.ingredienet.put(counter, ingredienetToBeSaved);

        ingredienetToBeSaved.setId(counter);

        Ingredient savedPerson = this.ingredienet.get(counter);

        return ingredienetToBeSaved;
    }

    public Ingredient update(long id, Ingredient input) {
        Ingredient output = this.ingredienet.get(id);

        output.setName(input.getName());
        output.setAllergen(input.isAllergen());
        output.setCostPrice(input.getCostPrice());
        output.setNumberOfStock(input.getNumberOfStock());

        return output;
    }

    public void delete(long id) {
        this.ingredienet.remove(id);
    }
}
