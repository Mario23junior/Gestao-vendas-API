package com.vendas.gestaovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
