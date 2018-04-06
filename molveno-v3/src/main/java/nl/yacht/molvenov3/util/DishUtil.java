package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Dish;

public class DishUtil {
    public static Dish update(Dish oldDish, Dish newDish){
        oldDish.setName(newDish.getName());
        oldDish.setDescription(newDish.getDescription());
        oldDish.setPrice(newDish.getPrice());
        oldDish.setIngredientList(newDish.getIngredientList());
        return oldDish;
    }
}
