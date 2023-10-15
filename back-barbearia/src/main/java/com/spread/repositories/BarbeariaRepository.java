package com.spread.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spread.entities.Barbearia;

public interface BarbeariaRepository extends JpaRepository<Barbearia, Long>{

    public Barbearia findByNome(String nome);
    
}
