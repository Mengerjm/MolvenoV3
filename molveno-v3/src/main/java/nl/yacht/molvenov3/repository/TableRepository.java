package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TableRepository {

    private ArrayList<Table> tables = new ArrayList<>();
    private Map<Long, Reservation> reservation = new HashMap<>();

    //Find all available tables
    public Iterable<Table> findAllAvailable() {
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
    public Table update(int tableNumber, Table table) {
        Table output = this.tables.get(tableNumber);
        output.setAvailable(table.isAvailable());
        output.setNumberOfSeats(table.getNumberOfSeats());
        output.setTableNumber(table.getTableNumber());
        return output;
    }

    //region New reservation - set reservation time to table

    //Online reservations, how many tables for number of people. Returns table number if possible, returns 0 of no tables available.
    public int[] howManyTables(Reservation reservation) {
        int fourChairCounter = 0;
        int twoChairCounter = 0;
        while (reservation.getAmountOfPeople() >= 3) {
            fourChairCounter++;
            reservation.setAmountOfPeople(reservation.getAmountOfPeople() - 4);
        }
        while (reservation.getAmountOfPeople() >= 1) {
            twoChairCounter++;
            reservation.setAmountOfPeople(reservation.getAmountOfPeople() - 2);
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
            if (reservationTime.equals(reservation.getReservationTime())) {
                table.getReservationTimes().remove(index);
            }
            index++;
        }
    }

//endregion

    //WORK IN PROGRESS IGNORE ERRORS
    //region Reservation edited - edit reservationtime from table

    //For every tablenumber that is saved in reservation, run the next method
    public int[] editReservedTables(int tableNumber, Reservation input) {
        for (int j = 0; j <= input.getTableNumber().length; j++) {
            this.findReservationtimesToEdit(input, j);
        }
        return null;
    }

    //For each table find the correct one and run the next method in it
    public void findReservationtimesToEdit(Reservation input, int j){
        for (Table table : tables) {
            int[] reservedTables = input.getTableNumber();
            if (reservedTables[j] == table.getTableNumber()) {
                this.removeReservationFromTable(table, input);
            }
        }
    }

    //For the correct table, find the correct reservationtime and delete it from the arraylist of the table
    public void edirReservationFromTable(Table table, Reservation input){
        int index = 0;
        for (LocalDateTime reservationTime : table.getReservationTimes()) {
            if (reservationTime.equals(input.getReservationTime())) {
                table.getReservationTimes().remove(index);
            }
            index++;
        }
    }

//endregion

}
