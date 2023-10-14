package com.spread.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_servicos")
public class Servicos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    

    private Double preco;
    
    @ManyToOne
    @JoinColumn(name = "barbearia_id")
    @JsonBackReference
    private Barbearia barbearia;

    public Servicos() {
    }

    public Servicos(Long id, String nome, Double preco, Barbearia barbearia) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.barbearia = barbearia;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Barbearia getBarbearia() {
        return this.barbearia; // Referência alterada
    }

    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
        if (barbearia != null && !barbearia.getServicos().contains(this)) {
            barbearia.getServicos().add(this);
        }
    }
}
