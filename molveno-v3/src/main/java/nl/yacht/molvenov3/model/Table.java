package nl.yacht.molvenov3.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Table {

    private int tableNumber;
    private int numberOfSeats;
    private boolean isAvailable; //Deze is nu voor nood/handmatig, check voor reserveringen?
    private static int tableNumberCounter = 1;
    private ArrayList<LocalDateTime> reservationTimes;

    //region getters and setters
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ArrayList<LocalDateTime> getReservationTimes() {
        return reservationTimes;
    }

    public void setReservationTimes(ArrayList<LocalDateTime> reservationTimes) {
        this.reservationTimes = reservationTimes;
    }

//endregion

    //Can table be used NOW, or is it reserved?
    public boolean canTableBeUsedNow(Table table){
        int counter = 0;
        for (LocalDateTime reserved:table.getReservationTimes()) {
            if(LocalDateTime.now().isAfter(reserved.minusHours(3)) && LocalDateTime.now().isBefore(reserved.plusHours(3))){
                counter++;
            }
        }
        if(counter == 0){
            return true; //It can be used
        }
        else return false; //It can not be used
    }

    //Table available for online reservation at reservation time?
    public boolean canTableBeReserved(Table table, LocalDateTime reservationTime){
        int counter = 0;
        for (LocalDateTime reserved:table.getReservationTimes()) {
            if(reservationTime.isAfter(reserved.minusHours(3)) && reservationTime.isBefore(reserved.plusHours(3))){
                counter++;
            }
        }
        if(counter == 0){
            return true; //It can be used
        }
        else return false; //It can not be used
    }

    public Table(){}

    public Table(int numberOfSeats){
        this.tableNumber = tableNumberCounter++;
        this.numberOfSeats = numberOfSeats;
        this.isAvailable = true;
    }

    public Table(int tableNumber, int numberOfSeats) {
        this.tableNumber = tableNumber;
        this.numberOfSeats = numberOfSeats;
        this.isAvailable = true;
    }

}

