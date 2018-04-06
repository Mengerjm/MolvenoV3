package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Order;
import nl.yacht.molvenov3.repository.CrudOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editSpecials")
public class OrderController {

    @Autowired
    private CrudOrderRepository crudOrderRepository;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public Order create(@RequestBody Order order) {
        return crudOrderRepository.save(order);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Order update(@RequestBody Order order) {
        return crudOrderRepository.save(order);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Order> getAll() {
        return crudOrderRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Order findById(@PathVariable long id) {
        return crudOrderRepository.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        crudOrderRepository.delete(id);
    }

}