package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReservationRepository {


    private static long counter = 0;

    private Map<Long, Reservation> reservation = new HashMap<>();

    public Iterable<Reservation> findAll() {
        Iterable<Reservation> result = this.reservation.values();

        return result;
    }

    public Reservation save(Reservation reservationToBeSaved) {

        counter++;

        reservationToBeSaved.setId(counter);
        this.reservation.put(counter, reservationToBeSaved);

        Reservation savedReservation = this.reservation.get(counter);
        return savedReservation;

    }

    public Reservation update(long id, Reservation input) {

        Reservation output = this.reservation.get(id);

        output.setFirstName(input.getFirstName());
        output.setLastName(input.getLastName());
        output.setAmountOfPeople(input.getAmountOfPeople());
        output.setReservationTIme(input.getReservationTime());
        //and so on, if u have more fields
        return output;
    }

    public void delete(long id) {
        this.reservation.remove(id);
    }

    public Reservation findById(long id) {
        return this.reservation.get(id);
    }
}


