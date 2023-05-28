package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogOutController {

	@GetMapping
	public String logoutUtente(HttpSession session) {

		if (session.getAttribute("utente") != null) {
			session.removeAttribute("utente");
		}

		return "redirect:/index";
	}
	
	@GetMapping("/admin")
	public String logoutAdmin(HttpSession session) {

		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin");

		}

		return "redirect:/index";
	}
}
