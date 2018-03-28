package nl.yacht.molvenov3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value="/")
public class FrontendController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String home(Map<String, Object> model){
        return "index";
    }

}
