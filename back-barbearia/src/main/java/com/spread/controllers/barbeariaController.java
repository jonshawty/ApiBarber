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
import com.spread.services.BarbeariaService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/api/barbearia")
public class barbeariaController {

    @Autowired
    private BarbeariaService barbeariaService;

    @GetMapping
    public List<Barbearia> ListarBarbearias() {
        return barbeariaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Barbearia> adicionarBarbearia(@RequestBody Barbearia barbearia) {
        barbeariaService.saveValidation(barbearia);
        Barbearia novaBarbearia = barbeariaService.save(barbearia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaBarbearia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbearia> atualizarBarbearia(@PathVariable Long id, @RequestBody Barbearia barbeariaAtualizada) {
        Optional<Barbearia> barbeariaExistente = barbeariaService.findById(id);
    
        if (barbeariaExistente.isPresent()) {
            Barbearia barbearia = barbeariaExistente.get();
    
            // Atualizar os campos necessários
            barbearia.setNome(barbeariaAtualizada.getNome());
            barbearia.setTelefone(barbeariaAtualizada.getTelefone());
    
            // Salvar as alterações no banco de dados
            barbeariaService.save(barbearia);
    
            return new ResponseEntity<>(barbearia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBarbearia(@PathVariable Long id) {
        Optional<Barbearia> barbeariaExistente = barbeariaService.findById(id);

        if (barbeariaExistente.isPresent()) {
            barbeariaService.deleteById(id);
            return ResponseEntity.noContent().build();  // Retorna um status 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // Retorna um status 404 Not Found
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbearia> buscarBarbeariaPorId(@PathVariable Long id) {
        Optional<Barbearia> barbearia = barbeariaService.findById(id);
        
        if (barbearia.isPresent()) {
            return new ResponseEntity<>(barbearia.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
