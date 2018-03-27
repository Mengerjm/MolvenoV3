package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class TableRepository {

    private ArrayList<Table> tables = new ArrayList<>();

    //Find all available tables
    public Iterable<Table> findAllAvailable(){
        ArrayList<Table> availableTables = new ArrayList<>();
        for (Table table:this.tables) {
            if (table.isAvailable() && table.canTableBeUsedNow(table)){
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    //Add new table
    public Table save(Table table){
        this.tables.add(table);
        return table;
    }

    //Delete table by tableNumber
    public void delete(int tableNumber){
        int counter = 0;
        for (Table table:tables) {
            if(table.getTableNumber()==tableNumber){
                this.tables.remove(counter);
            }
            counter++;
        }
    }

    //Toggle available/not available
    public Table update(int tableNumber, Table table){
        Table output = this.tables.get(tableNumber);
        output.setAvailable(table.isAvailable());
        output.setNumberOfSeats(table.getNumberOfSeats());
        output.setTableNumber(table.getTableNumber());
        return output;
    }

    //Online reservations, how many tables for number of people. Returns table number if possible, returns 0 of no tables available.
    public int howManyTables(Reservation reservation){
        int fourChairCounter = 0;
        int twoChairCounter = 0;
        while(reservation.getAmountOfPeople()>=3){
                fourChairCounter++;
                reservation.setAmountOfPeople(reservation.getAmountOfPeople()-4);
        }
        while(reservation.getAmountOfPeople()>=1){
                twoChairCounter++;
                reservation.setAmountOfPeople(reservation.getAmountOfPeople()-2);
        }
        return fourSeatTables(fourChairCounter, twoChairCounter, reservation);
    }

    //Find 4 seat tables and set them unavailable for online reservations
    public int fourSeatTables(int fourChairs, int twoChairs, Reservation reservation){
        int tableNumber = 0;
        for(Table table : tables){
            if(table.getNumberOfSeats()==4
                    && table.isAvailable()
                    && fourChairs>0
                    && table.canTableBeReserved(table, reservation.getReservationTime())){
                table.getReservationTimes().add(reservation.getReservationTime());
                fourChairs--;
                tableNumber = table.getTableNumber();
            }
        }
        return twoSeatTables(fourChairs*2+twoChairs, tableNumber, reservation);
    }

    //Find 2 seat tables and set them unavailable for online reservations
    public int twoSeatTables(int twoChairs, int tableNumber, Reservation reservation){
        for(Table table : tables){
            if(table.getNumberOfSeats()==4
                    && table.isAvailable()
                    && twoChairs>0
                    && table.canTableBeReserved(table, reservation.getReservationTime())){
                table.getReservationTimes().add(reservation.getReservationTime());
                twoChairs--;
                tableNumber = table.getTableNumber();
            }
        }
        if(twoChairs>0){
            return 0;
            //Not enough tables, send error message
        }
        else return tableNumber;
    }

}
