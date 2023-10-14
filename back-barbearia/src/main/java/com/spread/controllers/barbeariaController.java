package com.spread.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spread.entities.Barbearia;
import com.spread.repositories.BarbeariaRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/api/barbearia")
public class barbeariaController {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @GetMapping
    public List<Barbearia> ListarBarbearias() {

        List<Barbearia> list = barbeariaRepository.findAll();
        return list;
    }

    @PostMapping
    public ResponseEntity<Barbearia> adicionarProduto(@RequestBody Barbearia barbearia) {
        Barbearia novoProduto = barbeariaRepository.save(barbearia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbearia> atualizarBarbearia(@PathVariable Long id, @RequestBody Barbearia barbeariaAtualizada) {
        Optional<Barbearia> barbeariaExistente = barbeariaRepository.findById(id);
    
        if (barbeariaExistente.isPresent()) {
            Barbearia barbearia = barbeariaExistente.get();
    
            // Atualizar os campos necessários
            barbearia.setNome(barbeariaAtualizada.getNome());
            barbearia.setTelefone(barbeariaAtualizada.getTelefone());
    
            // Salvar as alterações no banco de dados
            barbeariaRepository.save(barbearia);
    
            return new ResponseEntity<>(barbearia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBarbearia(@PathVariable Long id) {
        Optional<Barbearia> barbeariaExistente = barbeariaRepository.findById(id);

        if (barbeariaExistente.isPresent()) {
            barbeariaRepository.deleteById(id);
            return ResponseEntity.noContent().build();  // Retorna um status 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // Retorna um status 404 Not Found
        }
    }


}
