package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TableRepository {

    private ArrayList<Table> tables = new ArrayList<>();
    private Map<Long, Reservation> reservation = new HashMap<>();

    //region New reservation - set reservation time to table

    //Assign tables automatically depending on availability and party size
    public List<Table> reserveTables(Reservation reservation, int numberOfGuests){
        int biggestTable = biggestTableAvailable(reservation);
        if(biggestTable<reservation.getAmountOfPeople()){
            return findTwoTables(reservation, 0, numberOfGuests);
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
            return reserveTables(reservation, numberOfGuests+1);
        }
    }

    //Find biggest table available
    public int biggestTableAvailable(Reservation reservation){
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
    public List<Table> findTwoTables(Reservation reservation, int index, int numberOfGuests){
        Table tableOne = tables.get(index);
        int biggestTable = biggestTableAvailable(reservation);
        for (Table table:tables) {
            if(table.canTableBeReserved(table, reservation.getReservationTime())
                    && !table.equals(tableOne)
                    && (table.getNumberOfSeats()+tableOne.getNumberOfSeats())==reservation.getAmountOfPeople()){
                List<Table> output = new ArrayList<>();
                output.add(tableOne);
                tableOne.getReservationTimes().add(reservation.getReservationTime());
                output.add(table);
                table.getReservationTimes().add(reservation.getReservationTime());
                return output;
            }
        }
        if(index<tables.size()){
            return findTwoTables(reservation, index+1, numberOfGuests);
        }
        else if (numberOfGuests<=biggestTable*2) {
            return findTwoTables(reservation, 0, numberOfGuests + 1);
        }
        else return null; //TODO Fix no table available exception
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
