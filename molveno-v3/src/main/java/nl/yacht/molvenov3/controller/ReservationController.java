package nl.yacht.molvenov3.controller;

import nl.yacht.molvenov3.model.Reservation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Reservation get() {
        Reservation result = new Reservation(1,"Natasja Eibring",4);

        return result;
    }


}
