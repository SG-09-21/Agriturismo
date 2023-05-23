package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard-utenti")
public class DashUtentiController {

    @GetMapping
    public String getPage() {

	return "dashboard-utenti";
    }
}
