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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.model.StatusTitulo;
import com.cobranca.model.Titulo;
import com.cobranca.repository.filter.TituloFilter;
import com.cobranca.service.TitulosService;

@Controller
@RequestMapping("/titulos")
public class TitulosController {

	@Autowired
	private TitulosService service;
	
	@GetMapping("/form")
	public ModelAndView form(Titulo titulo) {
		ModelAndView mv = new ModelAndView("cadastro-titulo");
		mv.addObject(titulo);
		return mv;
	}
	
	@PostMapping("/form")
	public ModelAndView salvar(@Valid Titulo titulo, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors())
			return form(titulo);
		
		try {
			service.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return new ModelAndView("redirect:/titulos");
		} catch (IllegalArgumentException e) {
			result.rejectValue("dataVencimento", null, "Data inválida.");
			return form(titulo);
		}
	}
	
	@GetMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		ModelAndView mv = new ModelAndView("pesquisa-titulos");
		mv.addObject("titulos", service.pesquisar(filtro));
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		return form(titulo);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView excluir(@PathVariable Integer codigo, RedirectAttributes attributes) {
		service.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return new ModelAndView("redirect:/titulos");
	}
	
	@PutMapping("/{codigo}/receber")
	public @ResponseBody String receber(@PathVariable Integer codigo) {
		Titulo titulo = service.receber(codigo);
		return titulo.getStatus().getDescricao();
	}
	
	@ModelAttribute(name = "todosStatus")
	public List<StatusTitulo> todosStatus() {
		return Arrays.asList(StatusTitulo.values());
	}
	
}