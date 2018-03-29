package nl.yacht.molvenov3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value="/tablemanager.html")
public class FrontendTableController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String home(Map<String, Object> model){
        return "tablemanager";
    }

}

