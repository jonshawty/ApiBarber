package com.spread.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spread.entities.Barbearia;
import com.spread.repositories.BarbeariaRepository;

@Service
public class BarbeariaService {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public List<Barbearia> findAll() {
        return barbeariaRepository.findAll();
    }
    

    public Barbearia findByNome(String nome) {
        return this.barbeariaRepository.findByNome(nome);
    }

    public void saveValidation(Barbearia model) {
        Barbearia banco = this.barbeariaRepository.findByNome(model.getNome());

        if (banco != null) {
            // Estou tentando inserir um nome que já existe, retorna erro
            if (model.getId() == null)
                throw new NegocioException("Barbearia " + model.getNome() + " já existe.");

            // Se o model tem id, é atualização.
            // E se estou atualizando um objeto com id diferente do banco,
            // é porque estou tentando salvar um objeto com nome já existente, retorna erro
            if (model.getId() != null && !model.getId().equals(banco.getId()))
                throw new NegocioException("Barbearia " + model.getNome() + " já existe.");
        }
    }
    

    public Barbearia save(Barbearia barbearia) {
        // Aqui você pode adicionar validações e outras lógicas antes de salvar
        return barbeariaRepository.save(barbearia);
    }

    public Optional<Barbearia> findById(Long id) {
        return barbeariaRepository.findById(id);
    }

    public void deleteById(Long id) {
        barbeariaRepository.deleteById(id);
    }

    // Outros métodos de serviço relacionados a Barbearia...
}