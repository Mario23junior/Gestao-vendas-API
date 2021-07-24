package com.vendas.gestaovendas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.Service.ProdutoService;
import com.vendas.gestaovendas.model.Produto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ControllerProduto {
    
	private ProdutoService produtoService;
	
	public ControllerProduto(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@ApiOperation(value = "Listando todos os dados", nickname = "buscarTodos")
	@GetMapping
	public List<Produto> listAll(@PathVariable Long codigoCategoria) {
		return produtoService.listAllProduto(codigoCategoria);
	}
	
	
	@ApiOperation(value = "Listar por codigo", nickname = "buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Produto>> listById(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
		Optional<Produto> produto = produtoService.listProdutoById(codigo, codigoCategoria); 
		return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}
}
