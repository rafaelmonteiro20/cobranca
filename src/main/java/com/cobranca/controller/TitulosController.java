package com.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/titulos")
public class TitulosController {

	@GetMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
}