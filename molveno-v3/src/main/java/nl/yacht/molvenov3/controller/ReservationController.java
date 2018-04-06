package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Reservation;
import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.CrudReservationRepository;
import nl.yacht.molvenov3.repository.CrudTableRepository;
import nl.yacht.molvenov3.util.ReservationUtil;
import nl.yacht.molvenov3.util.TableUtil;
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
        List<Table> tables = ReservationUtil.makeList(crudTableRepository.findAll());
        List<Table> reservedTables = ReservationUtil.reserveTables(reservation, reservation.getAmountOfPeople(), tables);
        if(reservedTables==null){
            return null; //TODO Throw exception reservation not possible
        }
        for (Table table:reservedTables) {
            update(table.getId(), table);
        }
        reservation.setReservedTable(reservedTables);
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
        Reservation reservation = this.crudReservationRepository.findOne(id);
        ReservationUtil.cancelReservedTables(reservation);
        this.crudReservationRepository.delete(id);
    }

    //Set tables unavailable when new reservation is made
    public Table update(long id, Table input) {
        Table oldTable = crudTableRepository.findOne(id);
        Table newTable = TableUtil.update(oldTable, input);
        return crudTableRepository.save(newTable);
    }

}

