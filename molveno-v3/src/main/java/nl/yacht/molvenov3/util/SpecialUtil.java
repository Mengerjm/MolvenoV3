package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Special;

public class SpecialUtil {
    public static Special update(Special oldSpecial, Special newSpecial){
        oldSpecial.setPrice(newSpecial.getPrice());
        oldSpecial.setDescription(newSpecial.getDescription());
        oldSpecial.setName(newSpecial.getName());
        oldSpecial.setDishes(newSpecial.getDishes());
        return oldSpecial;
    }
}
