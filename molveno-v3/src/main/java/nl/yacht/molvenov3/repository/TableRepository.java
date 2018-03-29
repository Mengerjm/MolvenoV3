package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TableRepository {

    private ArrayList<Table> tables = new ArrayList<>();
    private Map<Long, Reservation> reservation = new HashMap<>();
    private static int counter = 0;

    //Find all available tables
    public Iterable<Table> findAllAvailable() {
        return createAvailableList();
    }

    //Create list of available tables
    public Iterable<Table> createAvailableList(){
        ArrayList<Table> availableTables = new ArrayList<>();
        for (Table table : this.tables) {
            if (table.isAvailable() && table.canTableBeUsedNow(table)) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    //Find all tables
    public Iterable<Table> findAll() {
        return this.tables;
    }

    //Find single table by ID
    public Table findByID(int tableNumber) {
        for (Table table:tables) {
            if(tableNumber==table.getTableNumber()){
                return table;
            }
        }
        return null;
    }

    //Add new table
    public Table save(Table table) {
        this.tables.add(table);
        return table;
    }

    //Delete table by tableNumber
    public void delete(int tableNumber) {
        int counter = 0;
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                this.tables.remove(counter);
            }
            counter++;
        }
    }

    //Update table
    public Table update(int tableNumber, Table input) {
        for (Table table:this.tables) {
            if(table.getTableNumber()==tableNumber){
                table.setAvailable(input.isAvailable());
                table.setNumberOfSeats(input.getNumberOfSeats());
                table.setTableNumber(input.getTableNumber());
                return table;
            }
        }
        return input;
    }

    //Set table unavailable NOW
    public Table setUnavailable(int tableNumber) {
        for (Table table:this.tables) {
            if(table.getTableNumber()==tableNumber){
                table.getReservationTimes().add(LocalDateTime.now());
                return table;
            }
        }
        return null;
    }

    //region New reservation - set reservation time to table

    //Online reservations, how many tables for number of people. Returns table number if possible, returns 0 of no tables available.
    public int[] howManyTables(Reservation reservation) {
        int fourChairCounter = 0;
        int twoChairCounter = 0;
        int numberGuests = reservation.getAmountOfPeople();
        while (numberGuests - 4 >= 3) {
            fourChairCounter++;
            numberGuests -= 4;
        }
        while (numberGuests >= 1) {
            twoChairCounter++;
            numberGuests -= 2;
        }
        return fourSeatTables(fourChairCounter, twoChairCounter, reservation);
    }

    //Find 4 seat tables and set them unavailable for online reservations
    public int[] fourSeatTables(int fourChairs, int twoChairs, Reservation reservation) {
        int i = 0;
        int[] tableNumber = new int[5];
        for (Table table : tables) {
            if (table.getNumberOfSeats() == 4
                    && table.isAvailable()
                    && fourChairs > 0
                    && table.canTableBeReserved(table, reservation.getReservationTime())) {
                table.getReservationTimes().add(reservation.getReservationTime());
                fourChairs--;
                tableNumber[i] = table.getTableNumber();
                i++;
            }
        }
        return twoSeatTables(fourChairs * 2 + twoChairs, tableNumber, reservation, i);
    }

    //Find 2 seat tables and set them unavailable for online reservations
    public int[] twoSeatTables(int twoChairs, int[] tableNumber, Reservation reservation, int i) {
        for (Table table : tables) {
            if (table.getNumberOfSeats() == 4
                    && table.isAvailable()
                    && twoChairs > 0
                    && table.canTableBeReserved(table, reservation.getReservationTime())) {
                table.getReservationTimes().add(reservation.getReservationTime());
                twoChairs--;
                tableNumber[i] = table.getTableNumber();
                i++;
            }
        }
        if (twoChairs > 0) {
            return null;
            //Not enough tables, send error message
        } else return tableNumber;
    }

    //endregion

    //region Reservation deleted - remove reservationtime from table

    //For every tablenumber that is saved in reservation, run the next method
    public void cancelReservedTables(Reservation reservation) {
        for (int j = 0; j <= reservation.getTableNumber().length; j++) {
            this.findReservationtimesFromTables(reservation, j);
        }
    }

    //For each table find the correct one and run the next method in it
    public void findReservationtimesFromTables(Reservation reservation, int j){
        for (Table table : tables) {
            int[] reservedTables = reservation.getTableNumber();
            if (reservedTables[j] == table.getTableNumber()) {
                this.removeReservationFromTable(table, reservation);
            }
        }
    }

    //For the correct table, find the correct reservationtime and delete it from the arraylist of the table
    public void removeReservationFromTable(Table table, Reservation reservation){
        int index = 0;
        for (LocalDateTime reservationTime : table.getReservationTimes()) {
            if (reservationTime.isEqual(reservation.getReservationTime())) {
                table.getReservationTimes().remove(index);
            }
            index++;
        }
    }

//endregion

}
