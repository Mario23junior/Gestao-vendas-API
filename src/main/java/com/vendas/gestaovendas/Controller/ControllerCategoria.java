package com.vendas.gestaovendas.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.Service.CategoriaService;
import com.vendas.gestaovendas.dto.CategoriaResponseDTO;
import com.vendas.gestaovendas.model.Categoria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/v1/api/categorias")
public class ControllerCategoria {
    
	private CategoriaService categoriaService;
	
	public ControllerCategoria(CategoriaService categoriaService) {
		 this.categoriaService = categoriaService;
	}
	
	@ApiOperation(value = "Listar todos os dados",nickname = "buscarTodos")
	@GetMapping
	public List<CategoriaResponseDTO> listAll(){
		List<CategoriaResponseDTO> list = categoriaService.listAllData()
				.stream()
				.map(categoria -> CategoriaResponseDTO.converterParaCategoriaDTO(categoria))
				.collect(Collectors.toList());
		return list;
		
	}
	
	@ApiOperation(value = "Listando dados por id" ,nickname = "buscarPorId")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> listById(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaService.listByIdData(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
 	}
	
	@ApiOperation(value = "Salvando dados",nickname = "save")
	@PostMapping
	public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria) {
		 Categoria salvarCategoria = categoriaService.save(categoria);
		 return ResponseEntity
				   .status(HttpStatus.CREATED)
				   .body(salvarCategoria);
	}
	
	@ApiOperation(value = "Atualizando dados",nickname = "update")
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> update(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaService.atualizarData(codigo, categoria));
	}
	
    
	@ApiOperation(value = "Deletando dados por id",nickname = "delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{codigo}")
	public void delete(@PathVariable Long codigo) {
		 categoriaService.delete(codigo);
	}
}













