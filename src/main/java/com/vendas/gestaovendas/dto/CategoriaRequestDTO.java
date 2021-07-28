package com.vendas.gestaovendas.dto;

import com.vendas.gestaovendas.model.Categoria;

public class CategoriaRequestDTO {
 
	private String nome;

	public Categoria converterParaEntidade() {
		return new Categoria(nome);
	}
	 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
