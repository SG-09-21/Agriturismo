package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Prodotto;
import it.corso.service.ProdottoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard-catalogo")
public class DashCatalogoController {

    @Autowired
    private ProdottoService prodottoService;
    
    @GetMapping
    public String getPage(
	    Model model,
	    HttpSession session, 
	    @RequestParam(name = "ok", required = false) String ok) {

	if (session.getAttribute("admin") == null) {
	    return "redirect:/index";
	}
	
	model.addAttribute("prodotto", new Prodotto());
	model.addAttribute("ok", ok != null);
	model.addAttribute("prodotti", prodottoService.getProdotti());
	return "dashboard-catalogo";
    }

    @PostMapping
    public String inserisciProdotto(
	    Model model,
	    @ModelAttribute("prodotto") Prodotto prodotto) {
	
	prodottoService.registraProdotto(prodotto);
	return "redirect:/dashboard-catalogo?ok";
    }
}
