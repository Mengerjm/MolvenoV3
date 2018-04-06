package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Special;
import nl.yacht.molvenov3.repository.CrudSpecialRepository;
import nl.yacht.molvenov3.util.SpecialUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editSpecials")
public class SpecialController {

    @Autowired
    private CrudSpecialRepository crudSpecialRepository;


    @RequestMapping(value = "/newSpecial", method = RequestMethod.POST)
    public Special create(@RequestBody Special special) {
        return crudSpecialRepository.save(special);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Special update(@RequestBody Special special) {
        return crudSpecialRepository.save(special);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Special> getAll() {
        return crudSpecialRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Special findById(@PathVariable long id) {
        return crudSpecialRepository.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        crudSpecialRepository.delete(id);
    }

    @GetMapping(value = "/findall")
    public Iterable<Special> findAll() {
        return crudSpecialRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public Special findById(@PathVariable("id") Long id) {
        return crudSpecialRepository.findOne(id);
    }

    @PutMapping(value = "/update/{id}")
    public Special update(@PathVariable("id") Long id, @RequestBody Special input) {
        return crudSpecialRepository.save(SpecialUtil.update(crudSpecialRepository.findOne(id), input));
    }
}
