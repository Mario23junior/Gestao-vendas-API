package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.Exceptions.RegraNegocioDuplicateDataException;
import com.vendas.gestaovendas.model.Produto;
import com.vendas.gestaovendas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;
	private CategoriaService categoriaService;

	public ProdutoService(ProdutoRepository produtoRepository,CategoriaService categoriaService) {
		this.produtoRepository = produtoRepository;
		this.categoriaService = categoriaService;
 	}
	
	public List<Produto> listAllProduto(Long codigoProduto){
		return produtoRepository.findByCategoriaCodigo(codigoProduto);
	}
	
	public Optional<Produto> listProdutoById(Long codigo, Long codigoCategoria){
		return produtoRepository.buscarPorCodigo(codigo, codigoCategoria);
	}
	
	public Produto salvar(Produto produto) {
		validarCategoriaDoProdutoExiste(produto.getCategoria().getCodigo());
		ValidarProdutoDuplicado(produto);
		return produtoRepository.save(produto);
	}
	
	public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto) {
	   Produto produtoSalvar = validarProdutoExist(codigoProduto, codigoCategoria);
	   validarCategoriaDoProdutoExiste(codigoCategoria);
	   ValidarProdutoDuplicado(produto);
	   BeanUtils.copyProperties(produto, produtoSalvar, "codigo");
	   return produtoRepository.save(produtoSalvar);
	}
	
	private Produto validarProdutoExist(Long codigoProduto, Long codigoCategoria) {
 		Optional<Produto> produto = listProdutoById(codigoProduto, codigoCategoria);
 		if(produto.isEmpty()) {
 			throw new EmptyResultDataAccessException(1);
 		}
 		return produto.get();
	}

	private void ValidarProdutoDuplicado(Produto produto) {
		 Optional<Produto> findData = produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
		
		if(findData.isPresent() && findData.get().getCodigo() != produto.getCodigo()) {
			throw new RegraNegocioDuplicateDataException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
		}
	}
	
	public void validarCategoriaDoProdutoExiste(Long codigoCategoria) {
		if(codigoCategoria == null) {
			throw new RegraNegocioDuplicateDataException("Codigo da categoria não pode ser vazia");
		}
		if(categoriaService.listByIdData(codigoCategoria).isEmpty()) {
			throw new RegraNegocioDuplicateDataException(String.format("Codiga da categoria %s informado não existe no cadastro", codigoCategoria));
		}
	}
}