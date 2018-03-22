package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;


    //GET, haal iets van de server
    @GetMapping
    public Iterable<Reservation> findAll() {
        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        return reservations;
    }

    @GetMapping(value = "{id}")
    public Reservation findById(@PathVariable long id) {
        return this.reservationRepository.findById(id);
    }

    @PutMapping(value = "{id}")
    public Reservation update(@PathVariable long id, @RequestBody Reservation input) {

        return this.reservationRepository.update(id, input);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        this.reservationRepository.delete(id);
    }

    @PostMapping
    public Reservation save(@RequestBody Reservation reservation) {
        return this.reservationRepository.save(reservation);
    }
}
