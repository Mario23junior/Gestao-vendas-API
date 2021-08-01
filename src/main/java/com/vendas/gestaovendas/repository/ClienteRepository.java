package com.vendas.gestaovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByNome(String nome);
}
