package nl.yacht.molvenov3.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    private List<Ingredient> ingredients;

    public Drink() {

    }

    public Drink(String name, String description, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
