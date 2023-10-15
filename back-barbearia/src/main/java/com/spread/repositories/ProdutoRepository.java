package com.spread.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spread.entities.Servicos;

public interface ProdutoRepository extends JpaRepository<Servicos, Long> {

    List<Servicos> findByBarbeariaId(Long barbeariaId);
    
}
