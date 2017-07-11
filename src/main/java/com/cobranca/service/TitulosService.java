package com.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cobranca.model.Titulo;
import com.cobranca.repository.Titulos;
import com.cobranca.repository.filter.TituloFilter;

@Service
public class TitulosService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch(DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Data inválida.");
		}
	}
	
	public Titulo receber(Integer id) {
		Titulo titulo = titulos.findOne(id);
		
		if(titulo == null)
			throw new IllegalArgumentException("Título não encontrado.");
	
		titulo.receber();
		
		return titulos.save(titulo);
	}
	
	public void excluir(Integer id) {
		titulos.delete(id);
	}

	public List<Titulo> pesquisar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);
	}
	
}