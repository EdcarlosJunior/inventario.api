package com.gestao.inventario_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.inventario_api.model.Produto;
import com.gestao.inventario_api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Retorna todos os produtos do banco
    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }

    /**
     * O @PostMapping indica que este método recebe dados.
     * O @RequestBody "converte" o JSON que o usuário envia para um objeto Produto.
     */
    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        // Salvamos o produto recebido e o Spring retorna o objeto com o ID preenchido
        return repository.save(produto);
    }
}