package com.vendas.gestaovendas.Service;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.repository.CategoriaRepository;

@Service
public class CategoriaService {
   
	private CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
 	}
	
	
}
