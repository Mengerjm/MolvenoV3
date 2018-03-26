package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Table;
import nl.yacht.molvenov3.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/table")
public class TableController {

    @Autowired
    private TableRepository tables;

    //Add new table with tablenumber and number of seats
    @PostMapping(value = "/newtable")
    public Table save(@RequestBody Table table){
        return this.tables.save(table);
    }

    //Change availability of table with id
    @PutMapping(value = "/available/{id}")
    public Table update(@PathVariable("id") int tableNumber, @RequestBody Table table){
        return this.tables.update(tableNumber, table);
    }

    //Delete a table by tablenumber
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") int tableNumber){
        this.tables.delete(tableNumber);
    }

}
