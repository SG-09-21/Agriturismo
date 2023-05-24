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

import it.corso.model.Admin;
import it.corso.model.Utente;
import it.corso.service.AdminService;
import it.corso.service.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login-admin")
public class LoginAdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public String getPage(
		@RequestParam(name="le", required= false) String logError, 
		Model model, 
		HttpSession session) 
		{
			if(session.getAttribute("admin")!=null)
			    return "redirect:/dashboard-admin";
			model.addAttribute("admin", new Admin());
			model.addAttribute("logError", logError != null);
			return "login-admin";
		}
	
	@PostMapping
    public String gestioneLogin(
    		HttpSession session,
    		@RequestParam(name = "username", required = false) String username,
    		@RequestParam(name = "password", required = false) String password) {
    	if (adminService.controlloLoginAdmin(session, username, password)) {

    		return "redirect:/dashboard-admin";
    	}
    	return "redirect:/login-admin?le";
    }
}
