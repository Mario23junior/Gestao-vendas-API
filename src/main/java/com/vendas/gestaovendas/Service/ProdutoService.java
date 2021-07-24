package com.vendas.gestaovendas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.model.Produto;
import com.vendas.gestaovendas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
 	}
	
	public List<Produto> listAllProduto(Long codigoProduto){
		return produtoRepository.findByCategoriaCodigo(codigoProduto);
	}
	
	public Optional<Produto> listProdutoById(Long codigo, Long codigoCategoria){
		return produtoRepository.buscarPorCodigo(codigo, codigoCategoria);
	}
}
