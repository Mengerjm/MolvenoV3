package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TableRepository {

    private ArrayList<Table> tables = new ArrayList<>();
    private Map<Long, Reservation> reservation = new HashMap<>();

    //Find all available tables
    public Iterable<Table> findAllAvailable() {
        return createAvailableList();
    }

    //Create list of available tables
    public Iterable<Table> createAvailableList() {
        ArrayList<Table> availableTables = new ArrayList<>();
        for (Table table : this.tables) {
            if (table.canTableBeUsedNow(table)) {
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
        for (Table table : tables) {
            if (tableNumber == table.getTableNumber()) {
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
        for (Table table : this.tables) {
            if (table.getTableNumber() == tableNumber) {
                table.setNumberOfSeats(input.getNumberOfSeats());
                table.setTableNumber(input.getTableNumber());
                return table;
            }
        }
        return input;
    }

    //Set table unavailable NOW
    public Table setUnavailable(int tableNumber) {
        for (Table table : this.tables) {
            if (table.getTableNumber() == tableNumber) {
                table.getReservationTimes().add(LocalDateTime.now());
                return table;
            }
        }
        return null;
    }

    //region New reservation - set reservation time to table

    //Assign tables automatically depending on availability and party size
    public List<Table> reserveTables(Reservation reservation){
        int totalSeats = reservation.getAmountOfPeople();
        Table biggestTable = biggestTableAvailable(reservation);
        List<Table> output = reserveOneTable(reservation, totalSeats);
        if(!output.isEmpty()){
            return output;
        }
        else if(biggestTable.getNumberOfSeats()>reservation.getAmountOfPeople()){
            return findBestTable(totalSeats, reservation);
        }
        else return findMultipleTables(reservation, biggestTable);

    }

    //See if one table is available that has the exact right amount of seats
    public List<Table> reserveOneTable(Reservation reservation, int totalSeats){
        List<Table> output = new ArrayList<>();
        for (Table table: tables) {
            if(table.getNumberOfSeats()==totalSeats && table.canTableBeReserved(table, reservation.getReservationTime())){
                output.add(table);
                table.getReservationTimes().add(reservation.getReservationTime());
                return output;
            }
        }
        return output;
    }

    //Find smallest possible available table
    public List<Table> findBestTable(int totalSeats, Reservation reservation){
        List<Table> output = new ArrayList<>();
        int smallestTable = 0;
        Table outputTable = new Table();
        for (Table table:tables) {
            if(table.getNumberOfSeats()<smallestTable
                    && table.getNumberOfSeats()>totalSeats
                    && table.canTableBeReserved(table, reservation.getReservationTime())){
                smallestTable = table.getNumberOfSeats();
                outputTable = table;
            }
        }
        output.add(outputTable);
        outputTable.getReservationTimes().add(reservation.getReservationTime());
        return output;

    }

    //Find multiple tables
    public List<Table> findMultipleTables(Reservation reservation, Table biggestTable){
        int totalSeats = reservation.getAmountOfPeople() - biggestTable.getNumberOfSeats();
        List<Table> output = reserveOneTable(reservation, totalSeats);
        output.add(biggestTable);
        Table secondBiggestTable = biggestTableAvailable(reservation, biggestTable);
        if(!output.isEmpty()){
            return output;
        }
        else if(secondBiggestTable.getNumberOfSeats()>reservation.getAmountOfPeople()){
            List<Table> outputTables = findBestTable(totalSeats, reservation);
            outputTables.add(biggestTable);
            return outputTables;
        }
        else  throw new RuntimeException(); // FIX DIE SHIZZLE NO RESERVATION POSSIBLE
    }

    //Find biggest table available
    public Table biggestTableAvailable(Reservation reservation){
        int biggestTable = 0;
        Table outputTable = new Table();
        for (Table table:tables) {
            if(table.getNumberOfSeats()>biggestTable
                    && table.canTableBeReserved(table, reservation.getReservationTime())){
                biggestTable = table.getNumberOfSeats();
                outputTable = table;
            }
        }
        return outputTable;
    }

    //Find the second biggest table available, ignoreTable = biggest table from first iteration
    public Table biggestTableAvailable(Reservation reservation, Table ignoreTable){
        int biggestTable = 0;
        Table outputTable = new Table();
        for (Table table:tables) {
            if(table.getNumberOfSeats()>biggestTable
                    && !table.equals(ignoreTable)
                    && table.canTableBeReserved(table, reservation.getReservationTime())){
                biggestTable = table.getNumberOfSeats();
                outputTable = table;
            }
        }
        return outputTable;
    }

    //endregion

    //region Reservation deleted - remove reservationtime from table

    //For every table that is saved in reservation, run the next method
    public void cancelReservedTables(Reservation reservation) {
        if (reservation.getReservedTable() != null) {
            for (Table table : reservation.getReservedTable()) {
                removeReservationFromTable(table, reservation);
            }
        }
    }

    //For the correct table, find the correct reservationtime and delete it from the arraylist of the table
    public void removeReservationFromTable(Table table, Reservation reservation) {
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
