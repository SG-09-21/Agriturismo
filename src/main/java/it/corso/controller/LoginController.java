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
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UtenteService utenteService;
	
    @GetMapping
    public String getPage(HttpSession session,
    		Model model,
    		@RequestParam(name = "userError", required = false) String userError,
    		@RequestParam(name = "loginError", required = false) String loginError) {
//    	if (session != null) {
//    		model.addAttribute("userError", userError);
//    		return "redirect:/index/userError";
//    	}
//    	model.addAttribute("loginError", loginError);
    	return "login";
    }
    
    @PostMapping
    public String gestioneLogin(
    		HttpSession session,
    		@RequestParam(name = "username") String username,
    		@RequestParam(name = "password") String password) {
    	if (utenteService.controlloLogin(session, username, password)) {
    		return "redirect:/catalogo";
    	}
    	return "redirect:/login/loginError";
    }
}
