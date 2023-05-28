package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Utente;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/contatti")
public class ContattiController {

    @GetMapping
    private String getPage(
	    HttpSession session, Model model) {

	Utente utente = (Utente) session.getAttribute("utente");
	model.addAttribute("loggato", utente != null);
	return "contatti";
    }
}
