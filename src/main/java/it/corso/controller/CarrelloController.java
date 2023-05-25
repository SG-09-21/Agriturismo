package it.corso.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Prodotto;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

    @GetMapping
    public String getPage(
    		HttpSession session,
    		Model model, 
    		@RequestParam(name = "del", required = false) String del) {
	
	if (session.getAttribute("carrello") != null) {
	    @SuppressWarnings("unchecked")
	    List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
	    model.addAttribute("carrello", carrello);
	}
	model.addAttribute("del", del != null);
        return "carrello";
    }

    // TODO cod copiato da sistemare
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
}