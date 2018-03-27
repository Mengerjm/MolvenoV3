package nl.yacht.molvenov3.model;


import java.io.Serializable;

public class Reservation implements Serializable {

    private String firstName;
    private String lastName;
    private int amountOfPeople;
    private int reservationTime;
    private long id;


    public Reservation() {
    }

    public Reservation(String firstName, String lastName, int amountOfPeople, int reservationTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfPeople = amountOfPeople;
        this.reservationTime = reservationTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public int getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(int reservationTime) {
        this.reservationTime = reservationTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setReservationTIme(int reservationTIme) {
        this.reservationTime = reservationTIme;
    }


    /** public void cancel() {

     //vervang guest voor tablenumber

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

     }
     */
}