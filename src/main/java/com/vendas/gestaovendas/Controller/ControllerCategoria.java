package com.vendas.gestaovendas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.Service.CategoriaService;
import com.vendas.gestaovendas.model.Categoria;

@RestController
@RequestMapping("/v1/api/categorias")
public class ControllerCategoria {
    
	private CategoriaService categoriaService;
	
	public ControllerCategoria(CategoriaService categoriaService) {
		 this.categoriaService = categoriaService;
	}
	
	@GetMapping
	public List<Categoria> listAll(){
		return categoriaService.listAllData();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> listById(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaService.listByIdData(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
 	}
}
