package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginAdmin")
public class LoginAdminController {

    @GetMapping
    private String getPage() {

	return "loginAdmin";
    }

}
