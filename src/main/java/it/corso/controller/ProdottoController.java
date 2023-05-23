package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dettaglio-prodotto")
public class ProdottoController {

    @GetMapping
    private String getPage() {

	return "/dettaglio-prodotto";
    }
}
