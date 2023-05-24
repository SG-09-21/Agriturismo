package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.service.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("dashboard-utenti")
public class DashUtentiController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
	    HttpSession session, 
	    Model model) {

	if (session.getAttribute("admin") == null) {
	    return "redirect:/login-admin";
	}

	model.addAttribute("utenti", utenteService.getUtenti());

	return "dashboard-utenti";
    }
}
