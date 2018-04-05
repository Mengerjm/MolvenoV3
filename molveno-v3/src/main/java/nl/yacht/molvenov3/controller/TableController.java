package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.CrudTableRepository;
import nl.yacht.molvenov3.repository.ReservationRepository;
import nl.yacht.molvenov3.repository.TableRepository;
import nl.yacht.molvenov3.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/table")
public class TableController {

    @Autowired
    private CrudTableRepository crudTableRepository;
    //private TableRepository tables;
    @Autowired
    private CrudTableRepository tableRepository;
    //private ReservationRepository reservationRepository;

    //Show all available tables
    @GetMapping(value = "/available")
    public Iterable<Table> findAllAvailable() {
        Iterable<Table> allTables = this.crudTableRepository.findAll();
        return TableUtil.findAllAvailable(allTables);
    }

    //Show all tables
    @GetMapping(value = "/findall")
    public Iterable<Table> findAll() {
        return this.crudTableRepository.findAll();
    }

    //Get table by id
    @GetMapping(value="/get/{id}")
    public Table findById(@PathVariable("id") Long tableNumber){
        return this.crudTableRepository.findOne(tableNumber);
    }

    //Add new table with tablenumber and number of seats
    @PostMapping(value = "/newtable")
    public Table save(@RequestBody Table table) {
        return this.crudTableRepository.save(table);
    }

    //Change table characteristics
    @PutMapping(value = "/update/{id}")
    public Table update(@PathVariable("id") Long tableNumber, @RequestBody Table input) {
        return this.crudTableRepository.save(input);
    }

    //Set table unavailable now for random walk in guests
    @PutMapping(value = "/available/{id}")
    public Table setUnavailable(@PathVariable("id") Long tableNumber) {
        Table newTable = this.crudTableRepository.findOne(tableNumber);
        return save(newTable);
    }

    //Delete a table by tablenumber
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long tableNumber) {
        this.crudTableRepository.delete(tableNumber);
    }

}
