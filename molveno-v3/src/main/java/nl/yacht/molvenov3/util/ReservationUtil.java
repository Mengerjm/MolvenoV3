package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ReservationUtil {

    public static Reservation update(Reservation update, Reservation reservation){
        update.setFirstName(reservation.getFirstName());
        update.setLastName(reservation.getLastName());
        update.setAmountOfPeople(reservation.getAmountOfPeople());
        update.setEmail(reservation.getEmail());
        update.setTelephoneNumber(reservation.getTelephoneNumber());
        update.setReservationTime(reservation.getReservationTime());
        return update;
    }

    //Turn findAll() Iterable into List
    public static List<Table> makeList(Iterable<Table> tables){
        List<Table> alltables = new ArrayList<>();
        for (Table table:tables) {
            alltables.add(table);
        }
        return alltables;
    }

    //region New reservation - set reservation time to table

    //Assign tables automatically depending on availability and party size
    public static List<Table> reserveTables(Reservation reservation, int numberOfGuests, List<Table> tables){
        int biggestTable = biggestTableAvailable(reservation, tables);
        //If biggest table is smaller than number of guests, two tables are required
        if(biggestTable<reservation.getAmountOfPeople()){
            return findTwoTables(reservation, 0, numberOfGuests, tables);
        }
        else {
            //If a table with the right amount of seats is available, it is returned and reservation time is set
            for (Table table : tables) {
                if (table.getNumberOfSeats() == numberOfGuests && table.canTableBeReserved(table, reservation.getReservationTime())) {
                    List<Table> output = new ArrayList<>();
                    output.add(table);
                    table.getReservationTimes().add(reservation.getReservationTime());
                    return output;
                }
            }
            //Try and find a table that is 1 bigger than the number of guests
            return reserveTables(reservation, numberOfGuests+1, tables);
        }
    }

    //Find biggest table available
    public static int biggestTableAvailable(Reservation reservation, List<Table> tables){
        int biggestTable = 0;
        for (Table table:tables) {
            if(table.getNumberOfSeats()>biggestTable
                    && table.canTableBeReserved(table, reservation.getReservationTime())){
                biggestTable = table.getNumberOfSeats();
            }
        }
        return biggestTable;
    }

    //Find two tables
    public static List<Table> findTwoTables(Reservation reservation, int index, int numberOfGuests, List<Table> tables){
        Table tableOne = tables.get(index);
        int biggestTable = biggestTableAvailable(reservation, tables);
        //First try to find a combination of 2 tables that are exactly equal to amount of guests
        for (Table table:tables) {
            if(table.canTableBeReserved(table, reservation.getReservationTime())
                    && !table.equals(tableOne)
                    && (table.getNumberOfSeats()+tableOne.getNumberOfSeats())==numberOfGuests){
                List<Table> output = new ArrayList<>();
                output.add(tableOne);
                tableOne.getReservationTimes().add(reservation.getReservationTime());
                output.add(table);
                table.getReservationTimes().add(reservation.getReservationTime());
                return output;
            }
        }
        //Try again with a new combination of 2 tables as long as there are more tables in the db
        if(index<tables.size()){
            return findTwoTables(reservation, index+1, numberOfGuests, tables);
        }
        //As long as number of guests isn't twice as big as the amount of seats on the biggest table, try and look for a bigger table
        else if (numberOfGuests<=biggestTable*2) {
            return findTwoTables(reservation, 0, numberOfGuests+1, tables);
        }
        else return null;
    }

    //endregion

    //region Reservation deleted - remove reservationtime from table

    //For every table that is saved in reservation, run the next method
    public static List<Table> cancelReservedTables(Reservation reservation) {
        List<Table> reservedTables = reservation.getReservedTable();
        if (reservation.getReservedTable() != null) {
            for (Table table : reservedTables) {
                removeReservationFromTable(table, reservation);
            }
        }
        return reservedTables;
    }

    //For the correct table, find the correct reservationtime and delete it from the arraylist of the table
    public static void removeReservationFromTable(Table table, Reservation reservation) {
        int index = 0;
        try {
             for (LocalDateTime reservationTime : table.getReservationTimes()) {
                if (reservationTime.isEqual(reservation.getReservationTime())) {
                    table.getReservationTimes().remove(index);
                }
            }
            index++;
        }
        catch(ConcurrentModificationException cme) {
            // ignore for now
            // in the wild: log something ...
        }
    }

//endregion

}
