package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginAdmin")
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
