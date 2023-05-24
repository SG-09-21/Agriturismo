package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("dashboard-utenti")
public class DashUtentiController {

    @GetMapping
    public String getPage(
	    HttpSession session, 
	    Model model) {

	if (session.getAttribute("admin") == null) {
	    return "redirect:/login-admin";
	}

	return "dashboard-utenti";
    }
}
