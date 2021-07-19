package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.model.Categoria;
import com.vendas.gestaovendas.repository.CategoriaRepository;

@Service
public class CategoriaService {
   
	private CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
 	}
	
	public List<Categoria> listAllData(){
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> listByIdData(Long id){
		return categoriaRepository.findById(id);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}
