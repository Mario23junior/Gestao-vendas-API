package com.vendas.gestaovendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vendas.gestaovendas.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	   
	List<Produto> findByCategoriaCodigo(Long codigoCategoria);
	
	@Query("Select prod"
			+ " from Produto prod "
			+ "where prod.codigo = :codigo"
			+ " and prod.categoria.codigo = :codigoCategoria")
	Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria);

}
