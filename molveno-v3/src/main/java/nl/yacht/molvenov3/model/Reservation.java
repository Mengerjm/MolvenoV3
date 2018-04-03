package nl.yacht.molvenov3.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Reservation implements Serializable {

    private String firstName;
    private String lastName;
    private int amountOfPeople;
    private String telephoneNumber;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationTime;
    private long id;
    private List<Table> reservedTable;


    public Reservation() {
    }

    public Reservation(String firstName, String lastName, int amountOfPeople, String telephoneNumber, String email, LocalDateTime reservationTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfPeople = amountOfPeople;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.reservationTime = reservationTime;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Table> getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(List<Table> reservedTable) {
        this.reservedTable = reservedTable;
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