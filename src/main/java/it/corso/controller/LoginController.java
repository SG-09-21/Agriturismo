package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Utente;
import it.corso.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UtenteService utenteService;
	
    @GetMapping
    public String getPage(HttpSession session,
    		Model model,
    		@RequestParam(name = "loginError", required = false) String loginError,
    		@RequestParam(name = "reg", required = false) String reg,
    		@RequestParam(name = "ce", required = false) String credError,
    		@RequestParam(name = "errReg", required = false) String regError) {
    	
    	if (session.getAttribute("utente") != null) {
    		
    		return "redirect:/catalogo";
    	}

    	model.addAttribute("utente", new Utente());
    	model.addAttribute("loginError", loginError != null);
    	model.addAttribute("regError", regError != null);
    	model.addAttribute("reg", reg != null);
    	return "login";
    }
    
    @PostMapping("/accedi")
    public String gestioneLogin(
    		HttpSession session,
    		@RequestParam(name = "username", required = false) String username,
    		@RequestParam(name = "password", required = false) String password) {
    	if (utenteService.controlloLogin(session, username, password)) {

    		return "redirect:/catalogo";
    	}
    	return "redirect:/login?loginError";
    }
    
    @PostMapping
    public String inserisciUtente(
    		@Valid @ModelAttribute("utente") Utente utente,
	    BindingResult result, Model model) {
    	if(result.hasErrors()) {
	    model.addAttribute("credError", result.hasErrors());
	    return "login";
    	}
    	boolean assente = true;
    	for (Utente u : utenteService.getUtenti()) {
    		if (utente.getUsername().equalsIgnoreCase(u.getUsername())) {
    			assente = false;
    			break;
    		}
    	}
    	if (assente) {
    		utenteService.registraUtente(utente);
    		return "redirect:/login?reg";
    	}
    	
    	return "redirect:/login?errReg";
    	
    }
}
