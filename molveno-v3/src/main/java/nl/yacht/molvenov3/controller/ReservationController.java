package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.CrudReservationRepository;
import nl.yacht.molvenov3.repository.CrudTableRepository;
import nl.yacht.molvenov3.util.ReservationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private CrudReservationRepository crudReservationRepository;
    //private ReservationRepository reservationRepository;
    @Autowired
    private CrudTableRepository crudTableRepository;
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
        Iterable<Table> allTables = crudTableRepository.findAll();
        List<Table> tables = ReservationUtil.makeList(allTables);
        ReservationUtil.reserveTables(reservation,reservation.getAmountOfPeople(), tables);

        return this.crudReservationRepository.save(reservation);
    }

    @PutMapping(value = "{id}")
    public Reservation update(@PathVariable long id, @RequestBody Reservation reservation) {
        Reservation oldReservation = this.crudReservationRepository.findOne(id);
        Reservation newReservation = ReservationUtil.update(oldReservation, reservation);
        return this.crudReservationRepository.save(newReservation);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id){
        this.crudReservationRepository.delete(id);
        // Logica om tafel + reservering te verwijderen
    }

}

