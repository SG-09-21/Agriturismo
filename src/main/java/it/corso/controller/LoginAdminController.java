package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.service.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login-admin")
public class LoginAdminController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping
	public String getPage(
		@RequestParam(name="le", required= false) String logError, 
		Model model, 
		HttpSession session) 
		{
			if(session.getAttribute("utente")!=null)
			    return "redirect:/index";
			model.addAttribute("logError", logError != null);
			return "login-admin";
		}
	
}
