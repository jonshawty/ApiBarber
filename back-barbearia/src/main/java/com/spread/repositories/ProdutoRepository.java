package com.spread.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spread.entities.Servicos;

public interface ProdutoRepository extends JpaRepository<Servicos, Long> {
    
}
