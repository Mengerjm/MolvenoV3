package nl.yacht.molvenov3.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;
    private TypeOfCourse toc;
    private TypeOfDish tod;

    @OneToMany
    private List<Ingredient> ingredientList;

    public Dish(){

    }

    public Dish(String name, String description, List<Ingredient> ingredientList, double price, TypeOfCourse toc, TypeOfDish tod) {
        this.name = name;
        this.description = description;
        this.ingredientList = ingredientList;
        this.price = price;
        this.toc = toc;
        this.tod = tod;
    }

    public TypeOfCourse getToc() {
        return toc;
    }

    public void setToc(TypeOfCourse toc) {
        this.toc = toc;
    }

    public TypeOfDish getTod() {
        return tod;
    }

    public void setTod(TypeOfDish tod) {
        this.tod = tod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setId(Long id) {
        this.id = id;
    }
}
