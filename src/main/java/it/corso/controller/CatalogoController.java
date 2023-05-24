package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Prodotto;
import it.corso.model.Utente;
import it.corso.service.ProdottoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public String getPage(HttpSession session, Model model) {

	List<Prodotto> prodotti = prodottoService.getProdotti();

	model.addAttribute("prodotti", prodotti);
	if (session.getAttribute("utente") == null) {
	    // qui è dove impediamo all'utente di comprare cose se non è loggato
	    return "catalogo";
	}
	Utente utente = (Utente) session.getAttribute("utente");
	
	model.addAttribute("utente", utente);
	return "catalogo";
    }
}
