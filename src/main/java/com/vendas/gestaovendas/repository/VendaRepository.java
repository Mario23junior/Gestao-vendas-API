package com.vendas.gestaovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
   
	List<Venda> findByClienteCodigo(Long codigoCliente);
}
