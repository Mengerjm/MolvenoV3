package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Special;
import nl.yacht.molvenov3.repository.SpecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/editSpecials")
public class SpecialController {

    @Autowired
    private SpecialRepository specialRepository;

    @GetMapping
    public Iterable<Special> findAll() {
        Iterable<Special> specials = this.specialRepository.findAll();
        return specials;
    }

    @GetMapping(value = "{id}")
    public Special findById(@PathVariable long id) {
        return this.specialRepository.findById(id);
    }
}
