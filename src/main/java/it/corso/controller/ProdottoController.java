package it.corso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Prodotto;
import it.corso.model.Utente;
import it.corso.service.ProdottoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dettaglio-prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;
    

    @GetMapping
    private String getPage(
	    @RequestParam("id") int id,
	    @RequestParam(name = "del", required = false) String del,
	    HttpSession session, 
	    Model model) {

	if (session.getAttribute("carrello") != null) {
	    @SuppressWarnings("unchecked")
	    List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
	    model.addAttribute("carrello", carrello);
	    model.addAttribute("carrelloPieno", !carrello.isEmpty());
	} else {

	    List<Prodotto> carrello = new ArrayList<>();
	    model.addAttribute("carrello", carrello);
	    model.addAttribute("carrelloPieno", !carrello.isEmpty());
	}

	Utente utente = (Utente) session.getAttribute("utente");
	model.addAttribute("loggato", utente != null);
	Prodotto prodotto = prodottoService.getProdottoById(id);
	model.addAttribute("p", prodotto);
	model.addAttribute("utente", utente);
	model.addAttribute("del", del != null);
	return "/dettaglio-prodotto";
    }

    @GetMapping("/aggiungi")
    private String aggiungiAlCarrello(
	    @RequestParam("id") int id,
	    Model model, 
	    HttpSession session) {

	@SuppressWarnings("unchecked")
	List<Prodotto> carrello = session.getAttribute("carrello") == null ? new ArrayList<>()
		: (List<Prodotto>) session.getAttribute("carrello");

	carrello.add(prodottoService.getProdottoById(id));
	session.setAttribute("carrello", carrello);
	return "redirect:/catalogo?added";
    }

    @GetMapping("/rimuovi-prodotto")
    public String rimuoviProdottoCarrello(@RequestParam("id") int id, HttpSession session) {

	@SuppressWarnings("unchecked")
	List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

	for (Prodotto p : carrello) {
	    if (p.getId() == id) {
		carrello.remove(p);
		return "redirect:/catalogo?del";
	    }
	}

	return "redirect:/dettaglio-prodotto?del";
    }
}
