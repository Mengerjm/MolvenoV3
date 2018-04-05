package nl.yacht.molvenov3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value="/stock.html")
public class FrontendStockController {

    @GetMapping
    public String home(Map<String, Object> model){
        return "stock";
    }
}
