package it.corso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Ordine;
import it.corso.model.Prodotto;
import it.corso.service.OrdineService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard-ordini")
public class DashOrdiniController {

    @Autowired
    private OrdineService ordineService;

    private Ordine ordine;

    @GetMapping
    public String getPage(
	    HttpSession session,
	    Model model, @RequestParam(name = "id", required = false) Integer id) {

	

	if (id != null) {
	    ordine = ordineService.getOrdineById(id);
	    List<Prodotto> prodotti = ordine.getProdotti();
	    Map<Prodotto, Integer> mappaProdotti = new HashMap<>();

	    for (Prodotto p : prodotti) {

		if (mappaProdotti.containsKey(p)) {
		    int quantita = mappaProdotti.get(p);
		    mappaProdotti.put(p, quantita + 1);
		} else {
		    mappaProdotti.put(p, 1);
		}

	    }
	    model.addAttribute("prodotti", mappaProdotti);
	    model.addAttribute("selezionato", id != null);
	}
	
	
	List<Ordine> ordini = ordineService.getOrdini();
	model.addAttribute("ordini", ordini);

	return "dashboard-ordini";
    }

}
