package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.CrudTableRepository;
import nl.yacht.molvenov3.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/table")
public class TableController {

    @Autowired
    private CrudTableRepository crudTableRepository;
    //private TableRepository tables;

    //Show all available tables
    @GetMapping(value = "/available")
    public Iterable<Table> findAllAvailable() {
        Iterable<Table> allTables = crudTableRepository.findAll();
        return TableUtil.findAllAvailable(allTables);
    }

    //Show all tables
    @GetMapping(value = "/findall")
    public Iterable<Table> findAll() {
        return crudTableRepository.findAll();
    }

    //Get table by id
    @GetMapping(value = "/get/{id}")
    public Table findById(@PathVariable("id") Long id) {
        return crudTableRepository.findOne(id);
    }

    //Add new table with tablenumber and number of seats
    @PostMapping(value = "/newtable")
    public Table save(@RequestBody Table table) {
        return crudTableRepository.save(table);
    }

    //Change table characteristics
    @PutMapping(value = "/update/{id}")
    public Table update(@PathVariable("id") Long id, @RequestBody Table input) {
        Table oldTable = crudTableRepository.findOne(id);
        Table newTable = TableUtil.update(oldTable, input);
        return crudTableRepository.save(newTable);
    }

    //Set table unavailable now for random walk in guests
    @PutMapping(value = "/available/{id}")
    public Table setUnavailable(@PathVariable("id") Long id) {
        Table oldTable = crudTableRepository.findOne(id);
        Table newTable = TableUtil.setUnavailable(oldTable);
        return crudTableRepository.save(newTable);
    }

    //Delete a table by tablenumber
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long id) {
        crudTableRepository.delete(id);
    }

}
