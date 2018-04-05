package nl.yacht.molvenov3.model;

import java.util.List;

public class Special {
    private long id;
    private List<Dish> dishes;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceUsingExternalPrices(Special s, boolean useIngredientPrices){
        double price = 0.0;
        for(Dish d : s.getDishes()){
            if(useIngredientPrices){
                for(Ingredient i : d.getIngredientList()){
                    price += i.getPrice();
                }
            }else{
                price += d.getPrice();
            }
        }
        s.setPrice(price);
    }

    public Special(long id, List<Dish> dishes, double price) {
        this.id = id;
        this.dishes = dishes;
        this.price = price;
    }

    public Special() {
    }
}
