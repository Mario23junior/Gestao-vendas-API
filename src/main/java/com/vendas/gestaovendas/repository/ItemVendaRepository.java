package com.vendas.gestaovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vendas.gestaovendas.model.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{
   
	@Query("select new com.vendas.gestaovendas.model.ItemVenda("
			+ "iv.codigo, iv.quantidade, iv.precoVendido, iv.produto, iv.venda)"
			+ " from ItemVenda iv"
			+ " where iv.venda.codigo = :codigoVenda")
	List<ItemVenda> findByVendaPorCodigo(Long codigoVenda);
}
