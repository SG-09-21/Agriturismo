package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.service.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/LoginAdmin")
public class LoginAdminController {
	
	@Autowired
	UtenteService utenteService;
	
	@GetMapping
	public String getPage(@RequestParam(name="le", required= false) String logError, Model model, HttpSession session) 
		{
			if(session.getAttribute("utente")!=null)
				return "redirect:/reserved";
			model.addAttribute("logError", logError != null);
			return "LoginAdmin";
		}
	
	@PostMapping
	public String gestioneLogin() {
		
		return null;
	}
	
	
}
