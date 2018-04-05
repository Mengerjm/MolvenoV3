package nl.yacht.molvenov3.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private double price;

    @OneToMany
    private List<Ingredient> ingredientList;



    public Dish(){

    }

    public Dish(long id, String name, String description, List<Ingredient> ingredientList, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientList = ingredientList;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceUsingIngredientPrices(Dish d) {
        double price = 0.0;
        for(Ingredient i : d.getIngredientList()){
            price += i.getPrice();
        }
        d.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
