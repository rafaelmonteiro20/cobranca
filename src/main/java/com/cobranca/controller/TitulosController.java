package com.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.model.Titulo;
import com.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TitulosController {

	@Autowired
	private Titulos titulos;
	
	@GetMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@PostMapping
	public ModelAndView salvar(Titulo titulo, RedirectAttributes attributes) {
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return new ModelAndView("redirect:/titulos/novo");
	}
	
}