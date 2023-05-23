package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("struttura-servizi")
public class StrutturaController {

    @GetMapping
    public String getPage() {

	return "struttura-servizi";
    }
}
