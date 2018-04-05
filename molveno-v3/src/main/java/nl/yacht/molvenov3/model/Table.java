package nl.yacht.molvenov3.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tableNumber;
    private int numberOfSeats;

    //private boolean isAvailable; //Deze is nu voor nood/handmatig, check voor reserveringen?

    @ElementCollection

    private List<LocalDateTime> reservationTimes = new ArrayList<>();

    //region getters and setters
    public Long getTableNumber() {
        return tableNumber;
    }


    public void setTableNumber(Long tableNumber) {

        this.tableNumber = tableNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    public ArrayList<LocalDateTime> getReservationTimes() {
        return (ArrayList) reservationTimes;

    }

    public void setReservationTimes(List<LocalDateTime> reservationTimes) {
        this.reservationTimes = reservationTimes;
    }

    //endregion

    //Can table be used NOW, or is it reserved?
    public  boolean canTableBeUsedNow(){
        int counter = 0;
        for (LocalDateTime reserved:this.getReservationTimes()) {
            if(LocalDateTime.now().isAfter(reserved.minusHours(3)) && LocalDateTime.now().isBefore(reserved.plusHours(6))){
                reserved.minusHours(3);
                counter++;
            }
        }
       return counter == 0;
    }

    //Table available for online reservation at reservation time?
    public boolean canTableBeReserved(Table table, LocalDateTime reservationTime){
        int counter = 0;
        for (LocalDateTime reserved:table.getReservationTimes()) {
            if(reservationTime.isAfter(reserved.minusHours(3)) && reservationTime.isBefore(reserved.plusHours(6))){
                reserved.minusHours(3);
                counter++;
            }
        }
        if(counter == 0){
            return true; //It can be used
        }
        else return false; //It can not be used
    }

    public Table(){}


    public Table(Long tableNumber, int numberOfSeats) {
        this.tableNumber = tableNumber;
        this.numberOfSeats = numberOfSeats;
    }

}

