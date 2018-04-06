package nl.yacht.molvenov3.controller;


import nl.yacht.molvenov3.model.Menu;
import nl.yacht.molvenov3.repository.CrudMenuRepository;
import nl.yacht.molvenov3.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {


    /*
    @Autowired
    //private MenuRepository menuRepository;
    private CrudMenuRepository crudMenuRepository;


    // Todo this controller is kind of empty isn't it ;)

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Menu create(@RequestBody Menu menu) {
        return crudMenuRepository.save(menu);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Menu update(@RequestBody Menu menu) {
        return crudMenuRepository.save(menu);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Menu findById(@PathVariable long id) {
        return crudMenuRepository.findOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        crudMenuRepository.delete(id);
    }
    */
}
