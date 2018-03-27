

package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Ingredient;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;




@Repository
public class IngredientRepository {

    private boolean isEqual;
    private static long counter = 0;

    private Map<Long, Ingredient> ingredienet = new HashMap<>();


    //get all ingredients
    public Iterable<Ingredient> getAll() {
        Iterable<Ingredient> result = this.ingredienet.values();

        return result;
    }

    //save ingedient
    public Ingredient save(Ingredient ingredienetToBeSaved) {


        long equalCounter = 0;

        for(Ingredient i : this.ingredienet.values()){

            if(ingredienetToBeSaved.getName().equals(i.getName())){

                System.out.println("their is a equel in Map="+ equalCounter);

                isEqual= true;
                equalCounter++;
                break;
            }

            equalCounter++;

        }
        if (!isEqual){


            counter++;
            this.ingredienet.put(counter, ingredienetToBeSaved);

            ingredienetToBeSaved.setId(counter);

            Ingredient savedIngredient = this.ingredienet.get(counter);

            System.out.println("Add nieuw ingredient");
            return ingredienetToBeSaved;
        }
        else {

            //add stock of same ingredients
           Ingredient tepminpute =ingredienet.get(equalCounter);

           //add old stock plus new stock
           int addStock = ingredienetToBeSaved.getNumberOfStock()+tepminpute.getNumberOfStock();
             System.out.println(addStock);

             //save added stock + price
              tepminpute.setNumberOfStock(addStock);
              tepminpute.setCostPrice(ingredienetToBeSaved.getCostPrice());

            isEqual=false;
            System.out.println("Replace equal ingredient");

            return ingredienetToBeSaved;
        }

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
