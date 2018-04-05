package nl.yacht.molvenov3.util;

import nl.yacht.molvenov3.model.Table;
import java.util.ArrayList;
import java.util.List;

public class ReservationUtil {

    //region WIP
    public static void reserveRightTable(int amountOfPeople, Iterable<Table> allTables) {
        if (amountOfPeople > getMaxSeats(allTables, amountOfPeople)) {
            combineTables(amountOfPeople, getAvailableTables(allTables), 0);
        } else {
            for (Table t : allTables) {
                if (t.getNumberOfSeats() >= amountOfPeople) {
                    if (t.isAvailable()) {
                        t.setAvailable(false);
                    }
                } else {
                    reserveRightTable(amountOfPeople + 1, allTables);
                }
            }
        }
    }

    private static void combineTables(int amountOfPeople, List<Table> avTables, int counter) {
        Table table = avTables.get(counter);
        int avSeats =  table.getNumberOfSeats();

        for(Table t : avTables){
            if(!t.equals(table) && (avSeats + t.getNumberOfSeats() >= amountOfPeople)){
                t.setAvailable(false);
                table.setAvailable(false);
            }
        }

        if(table.isAvailable()){
            combineTables(amountOfPeople, avTables, counter +1);
        }
    }

    private static List<Table> getAvailableTables(Iterable<Table> allTables) {
        List<Table> l = new ArrayList<>();

        for(Table t : allTables)
        {
            if(t.isAvailable()){
                l.add(t);
            }
        }
        return l;
    }

    private static int getMaxSeats(Iterable<Table> allTables, int numberOfSeats) {
        int nos;

        if (numberOfSeats == 0) {
            nos = 0;
        } else {
            nos = numberOfSeats;
        }

        for (Table t : allTables) {
            if (t.getNumberOfSeats() >= nos) {
                getMaxSeats(allTables, t.getNumberOfSeats());
            }
        }
        return nos;
    }
    //endregion
}
