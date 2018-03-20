package nl.yacht.molvenov3;

import nl.yacht.molvenov3.model.*;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private static List<Drink> drinkList;
    private static List<Dish> dishList;
    private static List<Ingredient> ingredientList;
    private static List<Reservation> reservationList;
    private static List<Special> specialList;
    private static List<Order> orderList;
    private static List<Table> tableList;

    //region getters and setters

    public static List<Drink> getDrinkList() {
        return drinkList;
    }

    public static void setDrinkList(List<Drink> drinkList) {
        Restaurant.drinkList = drinkList;
    }

    public static List<Dish> getDishList() {
        return dishList;
    }

    public static void setDishList(List<Dish> dishList) {
        Restaurant.dishList = dishList;
    }

    public static List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public static void setIngredientList(List<Ingredient> ingredientList) {
        Restaurant.ingredientList = ingredientList;
    }

    public static List<Reservation> getReservationList() {
        return reservationList;
    }

    public static void setReservationList(List<Reservation> reservationList) {
        Restaurant.reservationList = reservationList;
    }

    public static List<Special> getSpecialList() {
        return specialList;
    }

    public static void setSpecialList(List<Special> specialList) {
        Restaurant.specialList = specialList;
    }

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static void setOrderList(List<Order> orderList) {
        Restaurant.orderList = orderList;
    }

    public static List<Table> getTableList() {
        return tableList;
    }

    public static void setTableList(List<Table> tableList) {
        Restaurant.tableList = tableList;
    }


    //endregion

    //region AddToList
    public static void addSpecialToList(Special s) {
        specialList.add(s);
    }

    public static void addDrinkToList(Drink d) {
        drinkList.add(d);
    }

    public static void addDishToList(Dish d) {
        dishList.add(d);
    }

    public static void addIngredientToList(Ingredient i) {
        ingredientList.add(i);
    }

    public static void addTableToList(Table t) {
        tableList.add(t);
    }

    public static void addOrderToList(Order o) {
        orderList.add(o);
    }

    public static void addReservationToList(Reservation r) {
        reservationList.add(r);
    }
    //endregion

    public void initAllLists() {
        drinkList = new ArrayList<>();
        dishList = new ArrayList<>();
        ingredientList = new ArrayList<>();
        reservationList = new ArrayList<>();
        specialList = new ArrayList<>();
        orderList = new ArrayList<>();
        tableList = new ArrayList<>();
    }

}
