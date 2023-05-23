package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Utente;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @GetMapping
    public String getPage(HttpSession session, Model model) {
	if (session.getAttribute("utente") == null) {
	    // qui è dove impediamo all'utente di comprare cose se non è loggato
	    return "catalogo";
	}
	Utente utente = (Utente) session.getAttribute("utente");
	model.addAttribute("utente", utente);
	return "catalogo";
    }
}
