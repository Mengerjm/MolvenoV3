package nl.yacht.molvenov3.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;

    @ManyToMany
    private List<Ingredient> ingredientList;



    public Drink(){

    }

    public Drink(long id, String name, String description, List<Ingredient> ingredientList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientList = ingredientList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
