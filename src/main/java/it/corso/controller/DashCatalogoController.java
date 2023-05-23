package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard-catalogo")
public class DashCatalogoController {

    @GetMapping
    public String getPage() {

	return "dashboard-catalogo";
    }
}
