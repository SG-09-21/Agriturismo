package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Prodotto;
import it.corso.service.ProdottoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dettaglio-prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;
    
    @GetMapping
    private String getPage(
	    @RequestParam("prodotto") int id,
	    HttpSession session, 
	    Model model) {

	Prodotto prodotto = prodottoService.getProdottoById(id);
	model.addAttribute("p", prodotto);
	return "/dettaglio-prodotto";
    }
}
