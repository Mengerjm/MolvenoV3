package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Dish;
import nl.yacht.molvenov3.model.Ingredient;
import nl.yacht.molvenov3.model.Special;

public class FinancialUtil {

    public static double getPriceUsingDishPrices(Special s) {
        double price = 0.0;
        for (Dish d : s.getDishes()) {
            price += d.getPrice();
        }
        return price;
    }

    public static double getPriceUsingIngredientPrices(Special s) {
        double price = 0.0;
        for (Dish d : s.getDishes()) {
            price += FinancialUtil.getPriceUsingIngredientPrices(d);
        }
        return price;
    }

    public static double getPriceUsingIngredientPrices(Dish d) {
        double price = 0.0;
        for (Ingredient i : d.getIngredients()) {
            price += i.getPrice();
        }
        return price;
    }
}
