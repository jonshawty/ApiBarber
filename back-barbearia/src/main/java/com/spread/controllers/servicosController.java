package com.spread.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spread.entities.Servicos;
import com.spread.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "/api/servicos")
public class servicosController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Servicos> getObjects() {

        List<Servicos> list = produtoRepository.findAll();
        return list;

    }

@PostMapping
public ResponseEntity<Servicos> adicionarProduto(@RequestBody Servicos servicos) {
    Servicos novoProduto = produtoRepository.save(servicos);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
}

}
