package com.vendas.gestaovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.model.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{
   
	List<ItemVenda> findByVendaCodigo(Long codigoVenda);
}
