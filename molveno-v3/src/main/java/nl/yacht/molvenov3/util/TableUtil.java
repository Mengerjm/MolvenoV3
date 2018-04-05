package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.CrudTableRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableUtil {

    //Find all tables that are available NOW
    public static Iterable<Table> findAllAvailable(Iterable<Table> allTables){
        List<Table> availableTables = new ArrayList<>();
        for (Table table: allTables) {
            if(table.canTableBeUsedNow()){
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    //Set a table unavailable NOW
    public static Table setUnavailable(Table table) {
        table.getReservationTimes().add(LocalDateTime.now());
        return table;
    }

    public static Table update(Table oldTable, Table newTable){
        oldTable.setTableNumber(newTable.getTableNumber());
        oldTable.setNumberOfSeats(newTable.getNumberOfSeats());
        return oldTable;
    }

    //WIP WIP WIP WIP WIP WIP Staat nog niet in de controller
    public static Table removeReservationFromTable(Table table, Reservation oldreservation) {
        int index = 0;
        for (LocalDateTime reservationTime : table.getReservationTimes()) {
            if (reservationTime.isEqual(oldreservation.getReservationTime())) {
                table.getReservationTimes().remove(index);
            }
            index++;
        }
        return table;
    }

}
