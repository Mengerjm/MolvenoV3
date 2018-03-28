package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.ReservationRepository;
import nl.yacht.molvenov3.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/table")
public class TableController {

    @Autowired
    private TableRepository tables;
    @Autowired
    private ReservationRepository reservationRepository;

    //Show all available tables
    @GetMapping(value = "/available")
    public Iterable<Table> findAllAvailable() {
        return this.tables.findAllAvailable();
    }

    //Show all tables
    @GetMapping(value = "/findall")
    public Iterable<Table> findAll() {
        return this.tables.findAll();
    }

    //Add new table with tablenumber and number of seats
    @PostMapping(value = "/newtable")
    public Table save(@RequestBody Table table) {
        return this.tables.save(table);
    }

    //Update tablenumber, numberOfSeats and isAvailable
    @PutMapping(value = "/available/{id}")
    public Table update(@PathVariable("id") int tableNumber, @RequestBody Table table) {
        return this.tables.update(tableNumber, table);
    }

    //Delete a table by tablenumber
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") int tableNumber) {
        this.tables.delete(tableNumber);
    }

}
