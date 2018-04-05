package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudReservationRepository extends CrudRepository<Reservation, Long> {
}
