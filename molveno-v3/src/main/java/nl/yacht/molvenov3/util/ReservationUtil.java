package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.CrudReservationRepository;
import nl.yacht.molvenov3.repository.CrudTableRepository;
import org.springframework.beans.factory.annotation.Autowired;

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

    //region New reservation - set reservation time to table

    //Assign tables automatically depending on availability and party size
    public static List<Table> reserveTables(Reservation reservation, int numberOfGuests, List<Table> tables){
        int biggestTable = biggestTableAvailable(reservation, tables);
        if(biggestTable<reservation.getAmountOfPeople()){
            return findTwoTables(reservation, 0, numberOfGuests, tables);
        }
        else {
            for (Table table : tables) {
                if (table.getNumberOfSeats() == numberOfGuests && table.canTableBeReserved(table, reservation.getReservationTime())) {
                    List<Table> output = new ArrayList<>();
                    output.add(table);
                    table.getReservationTimes().add(reservation.getReservationTime());
                    return output;
                }
            }
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
        if(index<tables.size()){
            return findTwoTables(reservation, index+1, numberOfGuests, tables);
        }
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

    //Turn findAll() Iterable into List
    public static List<Table> makeList(Iterable<Table> tables){
        List<Table> alltables = new ArrayList<>();
        for (Table table:tables) {
            alltables.add(table);
        }
        return alltables;
    }

//endregion

}
