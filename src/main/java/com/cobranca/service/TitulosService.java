package com.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cobranca.model.Titulo;
import com.cobranca.repository.Titulos;

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
}