package com.gestao.inventario_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.inventario_api.dto.ProdutoRequestDTO;
import com.gestao.inventario_api.dto.ProdutoResponseDTO;
import com.gestao.inventario_api.model.Produto;
import com.gestao.inventario_api.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Retorna produtos do banco
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        // Buscamos todos os produtos do banco
        var produtos = repository.findAll();
        
        // Convertemos a lista de Entidades para uma lista de DTOs
        var listaDTO = produtos.stream()
                               .map(ProdutoResponseDTO::new)
                               .toList();
                               
        return ResponseEntity.ok(listaDTO);
    }

    /**
     * O @PostMapping indica que este método recebe dados.
     * O @RequestBody "converte" o JSON que o usuário envia para um objeto Produto.
     */
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody @Valid ProdutoRequestDTO dados) {
        // 1. Converte o DTO que chegou na Entidade para o banco
        var produto = new Produto();
        produto.setNome(dados.nome());
        produto.setPreco(dados.preco());
        produto.setQuantidade(dados.quantidade());
        
        // 2. Salva no banco
        repository.save(produto);
        
        // 3. Retorna o DTO de resposta (Status 201 Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoResponseDTO(produto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
    	return repository.findById(id).map(produto -> {
    		repository.delete(produto);
    		return ResponseEntity.noContent().<Void>build(); // Retorna 204 (Sucesso, sem conteúdo)
    	}).orElse(ResponseEntity.notFound().build()); // Retorna 404 (Não encontrado)
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produtoAtualizado) {
        return repository.findById(id)
            .map(produto -> {
                produto.setNome(produtoAtualizado.getNome());
                produto.setPreco(produtoAtualizado.getPreco());
                produto.setQuantidade(produtoAtualizado.getQuantidade());
                Produto salvo = repository.save(produto);
                return ResponseEntity.ok(salvo); // Retorna 200 OK com o produto
            })
            .orElseGet(() -> {
                produtoAtualizado.setId(id);
                Produto salvo = repository.save(produtoAtualizado);
                return ResponseEntity.ok(salvo); // Também retorna ResponseEntity aqui
            }); // O ponto e vírgula final vai aqui
    }
}