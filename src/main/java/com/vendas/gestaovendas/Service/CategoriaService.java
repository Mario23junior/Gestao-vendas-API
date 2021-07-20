package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	public Categoria atualizarData(Long codigo, Categoria categoria) {
		Categoria categoriaSalvar = validarCategoriaExist(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalvar,"codigo");
		return categoriaRepository.save(categoriaSalvar);
 	}
	public Categoria validarCategoriaExist(Long codigo) {
		Optional<Categoria> categoria = listByIdData(codigo);
		if(categoria.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoria.get();
	}
}
