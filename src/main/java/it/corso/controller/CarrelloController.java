package it.corso.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Ordine;
import it.corso.model.Prodotto;
import it.corso.model.Utente;
import it.corso.service.OrdineService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

	@Autowired
	private OrdineService ordineService;
	
    @GetMapping
    public String getPage(
    		HttpSession session,
    		Model model, 
    		@RequestParam(name = "del", required = false) String del,
    		@RequestParam(name = "ordineOk", required = false) String ordineOk) {
	
	if (session.getAttribute("carrello") != null) {
	    @SuppressWarnings("unchecked")
	    List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
	    model.addAttribute("carrello", carrello);
	}
	model.addAttribute("del", del != null);
	model.addAttribute("ordineOk", ordineOk != null);
        return "carrello";
    }

	@GetMapping("/rimuovi-prodotto")
	public String rimuoviProdottoCarrello(@RequestParam("id") int id, HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

		for (Prodotto p : carrello) {
			if (p.getId() == id) {
				carrello.remove(p);
				return "redirect:/carrello?del";
			}
		}

		return "redirect:/carrello";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/aggiungi-ordine")
	public String effettuaOrdine(
			Model model,
			HttpSession session
			) {
		LocalDate data = LocalDate.now();
		List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
		Utente utente = (Utente) session.getAttribute("utente");
		
		ordineService.registraOrdine(new Ordine(), data, utente, carrello);
		carrello.clear();
		
		return "redirect:/carrello?ordineOk";
		
	}
}