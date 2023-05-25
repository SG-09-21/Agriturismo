package it.corso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Prodotto;
import it.corso.service.ProdottoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    
    public String getPage(
    		@RequestParam(name = "id", required = false) int id,
    		HttpSession session,
    		Model model) {
        @SuppressWarnings("unchecked")
		List<Integer> carrello = (List<Integer>) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new ArrayList<>();
        }

        List<Prodotto> prodotti = new ArrayList<>();
        for (Integer prodottoId : carrello) {
            Prodotto prodotto = prodottoService.getProdottoById(prodottoId);
            prodotti.add(prodotto);
        }

        model.addAttribute("carrello", prodotti);
        return "carrello";
    }

    // TODO cod copiato da sistemare
    @GetMapping("/rimuovi-prodotto")
    public String rimuoviProdottoCarrello(@RequestParam("id") int id, HttpSession session) {
        @SuppressWarnings("unchecked")
		List<Integer> carrello = (List<Integer>) session.getAttribute("carrello");
        if (carrello != null) {
            carrello.remove(Integer.valueOf(id));
            session.setAttribute("carrello", carrello);
        }
        return "redirect:/carrello";
    }
}