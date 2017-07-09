package com.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.model.StatusTitulo;
import com.cobranca.model.Titulo;
import com.cobranca.repository.Titulos;
import com.cobranca.service.TitulosService;

@Controller
@RequestMapping("/titulos")
public class TitulosController {

	@Autowired
	private Titulos titulos;
	
	@Autowired
	private TitulosService service;
	
	@GetMapping("/novo")
	public ModelAndView novo(Titulo titulo) {
		return new ModelAndView("CadastroTitulo");
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Titulo titulo, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors())
			return novo(titulo);
		
		try {
			service.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return new ModelAndView("redirect:/titulos/novo");
		} catch (IllegalArgumentException e) {
			result.rejectValue("dataVencimento", null, "Data inválida.");
			return novo(titulo);
		}
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", titulos.findAll());
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView edicao(@PathVariable("id") Titulo titulo) {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject(titulo);
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public ModelAndView excluir(@PathVariable Integer id, RedirectAttributes attributes) {
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return new ModelAndView("redirect:/titulos");
	}
	
	@ModelAttribute(name = "todosStatus")
	public List<StatusTitulo> todosStatus() {
		return Arrays.asList(StatusTitulo.values());
	}
	
}