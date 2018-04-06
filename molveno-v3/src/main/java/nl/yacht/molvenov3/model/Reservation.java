package nl.yacht.molvenov3.model;


import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private int amountOfPeople;
    private String telephoneNumber;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:00")
    private LocalDateTime reservationTime;
    @ManyToMany
    private List<Table> reservedTable;

    //region Getters and Setters

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

    //endregion

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

}