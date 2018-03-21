package nl.yacht.molvenov3.model;

import java.util.ArrayList;

public class Ingredient {

    private String name;
    private boolean isAllergen;//Enum worden
    //private Distributor distributor;
    private int numberOfStock;
    private double costPrice;
    private long id;//tijdelijk om werkend te krijgen


    public Ingredient(){}

    public Ingredient(String name, boolean isAllergen, int numberOfStock, double costPrice) {
        this.name = name;
        this.isAllergen = isAllergen;
        this.numberOfStock = numberOfStock;
        this.costPrice = costPrice;
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

    public boolean isAllergen() {
        return isAllergen;
    }

    public void setAllergen(boolean isAllergen) {
        this.isAllergen = isAllergen;
    }

    public int getNumberOfStock() {
        return numberOfStock;
    }

    public void setNumberOfStock(int numberOfStock) {
        this.numberOfStock = numberOfStock;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }


}
