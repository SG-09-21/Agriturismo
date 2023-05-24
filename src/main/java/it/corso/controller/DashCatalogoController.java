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
	    @RequestParam(name = "ok", required = false) String ok,
	    @RequestParam(name = "id", required = false) Integer id,
	    @RequestParam(name = "del", required = false) String del) {

	if (session.getAttribute("admin") == null) {
	    return "redirect:/login-admin";
	}
	
	Prodotto prodotto = id == null ? new Prodotto() : prodottoService.getProdottoById(id);

	model.addAttribute("del", del != null);
	model.addAttribute("edit", id != null);
	model.addAttribute("prodotto", prodotto);
	model.addAttribute("ok", ok != null);
	model.addAttribute("prodotti", prodottoService.getProdotti());
	return "dashboard-catalogo";
    }

    @GetMapping("/elimina")
    public String eliminaProdotto(
	    @RequestParam("id") int id) {
	prodottoService.cancellaProdotto(prodottoService.getProdottoById(id));
	return "redirect:/dashboard-catalogo?del";
    }

    @PostMapping
    public String inserisciProdotto(
	    Model model,
	    @ModelAttribute("prodotto") Prodotto prodotto) {
	
	prodottoService.registraProdotto(prodotto);
	return "redirect:/dashboard-catalogo?ok";
    }
}
