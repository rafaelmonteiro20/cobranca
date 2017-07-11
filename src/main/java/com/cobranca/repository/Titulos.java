package com.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobranca.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Integer> {

	List<Titulo> findByDescricaoContaining(String descricao);
	
}