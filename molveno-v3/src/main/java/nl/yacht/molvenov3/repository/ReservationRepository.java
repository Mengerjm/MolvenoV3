package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReservationRepository {

    private static long counter = 0;

    private Map<Long, Reservation> reservationMap = new HashMap<>();

    public Iterable<Reservation> findAll() {
        Iterable<Reservation> result = this.reservationMap.values();

        return result;
    }

    public Reservation save(Reservation personToBeSaved) {

        counter++;
        this.reservationMap.put(counter, personToBeSaved);

        personToBeSaved.setId(counter);

        Reservation savedReservation = this.reservationMap.get(counter);

        return savedReservation;
    }

    public Reservation update(long id, Reservation input) {
        Reservation output = this.reservationMap.get(id);

        output.zetLocalDateTimeBasedOnLocalDateTime(input.getReservationTime());
        output.setNumberOfPeople(input.getNumberOfPeople());
        output.setGuest(input.getGuest());
        output.setTableNumber(input.getTableNumber());
        //and so on, if u have more fields
        return output;
    }

    public void delete(long id) {
        this.reservationMap.remove(id);
    }

    public Reservation findById(long id) {
        return this.reservationMap.get(id);
    }
}
