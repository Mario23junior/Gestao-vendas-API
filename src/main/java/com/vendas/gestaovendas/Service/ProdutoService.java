package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

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
	
	private void ValidarProdutoDuplicado(Produto produto) {
		 Optional<Produto> findData = produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
		
		if(findData.isPresent()) {
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