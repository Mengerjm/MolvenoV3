package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.CrudTableRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableUtil {

    public static Iterable<Table> findAllAvailable(Iterable<Table> allTables){
        List<Table> availableTables = new ArrayList<>();
        for (Table table: allTables) {
            if(table.canTableBeUsedNow()){
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    public static Table setUnavailable(Table table) {
        table.getReservationTimes().add(LocalDateTime.now());
        return table;
    }
}
