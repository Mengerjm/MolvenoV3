package nl.yacht.molvenov3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
public class Menu  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    //geen idee wat dit moet worden??? @ManyToMany @OneToMany @ManyToOne
    private List<Drink> drinks;
    private List<Dish> dishes;
    private List<Special> specials;
    private Order order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Special> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu(){

    }

    public Menu(List<Drink> drinks, List<Dish> dishes, List<Special> specials, Order order) {
        this.drinks = drinks;
        this.dishes = dishes;
        this.specials = specials;
        this.order = order;
    }
}
