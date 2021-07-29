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

import com.vendas.gestaovendas.Service.ProdutoService;
import com.vendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import com.vendas.gestaovendas.model.Produto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria/{codigoCategoria}/produto")
public class ControllerProduto {

	private ProdutoService produtoService;

	public ControllerProduto(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@ApiOperation(value = "Listando todos os dados", nickname = "buscarTodos")
	@GetMapping
	public List<ProdutoResponseDTO> listAll(@PathVariable Long codigoCategoria) {
		return produtoService.listAllProduto(codigoCategoria).stream()
				.map(produto -> ProdutoResponseDTO.converterParaProdutoDTO(produto))
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Listar por codigo", nickname = "buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Produto>> listById(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
		Optional<Produto> produto = produtoService.listProdutoById(codigo, codigoCategoria);
		return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvando produto na base de dados", nickname = "SalvarProduto")
	@PostMapping
	public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto, @PathVariable Long codigoCategoria) {
		Produto salvarData = produtoService.salvar(produto, codigoCategoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarData);
	}

	@ApiOperation(value = "Atualizando informacoes de produtos", nickname = "AtualizandoProduto")
	@PutMapping("/{codigoProduto}")
	public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long codigoCategoria,
			@PathVariable Long codigoProduto, @RequestBody Produto produto) {
		Produto salvarNovosDados = produtoService.atualizar(codigoCategoria, codigoCategoria, produto);
		return ResponseEntity.ok(salvarNovosDados);
	}

	@ApiOperation(value = "deletando informacoes de produtos", nickname = "deletandoProduto")
	@DeleteMapping("/{codigoProduto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
		produtoService.deletar(codigoCategoria, codigoProduto);
	}
}
