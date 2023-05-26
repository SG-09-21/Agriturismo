package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Ordine;
import it.corso.model.Prodotto;
import it.corso.model.Utente;
import it.corso.service.OrdineService;
import it.corso.service.ProdottoService;
import it.corso.service.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard-ordini")
public class DashOrdiniController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private ProdottoService prodottoService;

    private Ordine ordine;

    @GetMapping
    public String getPage(
	    HttpSession session,
	    Model model, @RequestParam(name = "id", required = false) Integer id) {

	ordine = id == null ? new Ordine() : ordineService.getOrdineById(id);

	List<Utente> utenti = utenteService.getUtenti();
	List<Prodotto> prodotti = prodottoService.getProdotti();

	if (id != null) {
	    for (Prodotto p : prodotti) {
		for (Prodotto pInOrdine : ordine.getProdotti()) {
		    if (p.getId() == pInOrdine.getId()) {
			p.setIncluso(true);
		    }
		}
	    }
	}

	model.addAttribute("ordini", ordineService.getOrdini());
	model.addAttribute("clienti", utenti);
	model.addAttribute("prodotti", prodotti);
	model.addAttribute("ordine", ordine);
	return "dashboard-ordini";
    }
    
    @PostMapping("/registraordine")
    public String registraOrdine(
	    @RequestParam("data") String data,
	    @RequestParam("cliente") int idUtente,
	    @RequestParam(name = "prodotti", required = false) int[] idProdotti) {
	
	if (idProdotti == null) {
	    return "redirect:/ordini";
	}
	// FIXME qui c'è l'errore. Bisogna decidere cosa può fare effettivamente l'admin
	ordineService.registraOrdine(ordine, data, idUtente, idProdotti);
	return "redirect:/ordini";
    }

    @GetMapping("cancellaordine")
    public String cancellaOrdine(@RequestParam("id") int id) {
	ordineService.cancellaOrdine(ordineService.getOrdineById(id));
	return "redirect:/ordini";
    }
}
