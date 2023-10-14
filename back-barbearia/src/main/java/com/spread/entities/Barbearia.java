package com.spread.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_barbearia")
public class Barbearia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String nome;


    private String telefone;

    @OneToMany(mappedBy = "barbearia") 
    @JsonManagedReference
    private List<Servicos> servicos = new ArrayList<>();

    public Barbearia() {
    }

    public Barbearia(long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public void addServico(Servicos servico) {
        servicos.add(servico);
        servico.setBarbearia(this);
    }

    public void removeServico(Servicos servico) {
        servicos.remove(servico);
        servico.setBarbearia(null);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Servicos> getServicos() {
        return this.servicos;
    }

    public void setServicos(List<Servicos> servicos) {
        this.servicos = servicos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barbearia that = (Barbearia) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
