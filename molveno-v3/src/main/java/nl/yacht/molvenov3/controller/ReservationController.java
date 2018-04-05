package nl.yacht.molvenov3.controller;


import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.ReservationRepository;
import nl.yacht.molvenov3.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {


    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TableRepository tableRepository;

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
        this.tableRepository.cancelReservedTables(this.reservationRepository.findById(id));
        Reservation output = this.reservationRepository.update(id, input);
        output.setReservedTable(this.tableRepository.howManyTables(input));
        return output;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        this.tableRepository.cancelReservedTables(this.reservationRepository.findById(id));
        this.reservationRepository.delete(id);
    }

    @PostMapping
    public Reservation save(@RequestBody Reservation reservation) {
        List<Table> newTable = this.tableRepository.howManyTables(reservation);
        reservation.setReservedTable(newTable);
        return this.reservationRepository.save(reservation);
    }
}

