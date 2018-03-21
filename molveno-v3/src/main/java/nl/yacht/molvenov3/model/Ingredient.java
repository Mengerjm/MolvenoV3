package nl.yacht.molvenov3.model;

public class Ingredient {

    private String name;
    private boolean isAllergen;//Enum worden
    //private Distributor distributor;
    private int numberOfStock;
    private double costPrice;


    public Ingredient(){}

    public Ingredient(String name, boolean isAllergen, int numberOfStock, double costPrice) {
        this.name = name;
        this.isAllergen = isAllergen;
        this.numberOfStock = numberOfStock;
        this.costPrice = costPrice;
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

    public void setAllergen(boolean allergen) {
        isAllergen = allergen;
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
