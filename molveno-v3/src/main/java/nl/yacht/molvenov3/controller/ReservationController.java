package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.repository.CrudReservationRepository;
import nl.yacht.molvenov3.repository.CrudTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private CrudReservationRepository crudReservationRepository;
    //private ReservationRepository reservationRepository;
    @Autowired
    private CrudTableRepository tableRepository;
    //private TableRepository tableRepository;

    @GetMapping
    public Iterable<Reservation> findAll() {
        return this.crudReservationRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Reservation findById(@PathVariable long id) {
        return this.crudReservationRepository.findOne(id);
    }

    @PostMapping
    public Reservation save(@RequestBody Reservation reservation) {
        return this.crudReservationRepository.save(reservation);
        //  Logica om tafel te reserveren
    }

    @PutMapping(value = "{id}")
    public Reservation update(@PathVariable long id, @RequestBody Reservation reservation) {
        Reservation update = this.crudReservationRepository.findOne(id);
        update.setFirstName(reservation.getFirstName());
        update.setLastName(reservation.getLastName());
        update.setAmountOfPeople(reservation.getAmountOfPeople());
        update.setEmail(reservation.getEmail());
        update.setTelephoneNumber(reservation.getTelephoneNumber());
        update.setReservationTime(reservation.getReservationTime());
        // and for all the fields of the Reservation or create a move constructor

        return this.crudReservationRepository.save(update);
        // Logica om tafelreservering aan te passen
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id){
        this.crudReservationRepository.delete(id);
        // Logica om tafel + reservering te verwijderen
    }

}

