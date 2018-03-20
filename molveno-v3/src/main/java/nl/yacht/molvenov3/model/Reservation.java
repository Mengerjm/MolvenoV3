package nl.yacht.molvenov3.model;

import org.hibernate.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation {

    private LocalDateTime reservationTime;
    private int numberOfPeople;
    private String guest;
    private int tableNumber;

    public Reservation() {

    }

    public Reservation(/**LocalDateTime reservationTime ,*/int numberOfPeople, String guest, int tableNumber) {
        // this.reservationTime = reservationTime;
        this.numberOfPeople = numberOfPeople;
        this.guest = guest;
        this.tableNumber = tableNumber;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    public void cancel() {

        //vervang guest voor tablenumber
        /**
        public void cancel (Guest guest){
            ArrayList<Reservation> reservationList = Restaurant.getReservationList();
         // Go through the list of reservations
            int index = 0;
            for (Reservation res : reservationList) {
         // if tablenumber.equals(res.gettablenumber)
                if (guest.equals(res.guest)) {
                    reservationList.remove(index);

                    // Als de tafel gelijk is aan de gereserveerde tafel: maak de tafel available
                    for (Table tabl : Restaurant.getTableList()) {
                        if (tabl.getTableNumber() == res.tableNumber) {
                            tabl.setAvailable(true);
                        }
                    }

                }
                index++;
                // Gaat in de lijst verder zoeken index +1
            }
        }
        */
    }
}
