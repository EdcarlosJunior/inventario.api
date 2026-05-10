package com.gestao.inventario_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.inventario_api.model.Produto;
import com.gestao.inventario_api.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Retorna todos os produtos do banco
    @GetMapping("/{id}")
    // ResponseEntity<Produto> permite retornar o objeto junto com o código HTTP (200, 404, etc.)
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
    	// findById retorna um "Optional". É como uma caixa que pode ou não ter um produto dentro.
        return repository.findById(id)
		        // Se o produto existir (caixa cheia), o .map entra em ação:
		        // Ele pega o produto e retorna um "OK" (Status 200) com o produto no corpo da resposta.
		        .map(produto -> ResponseEntity.ok(produto))
		        
		        // Se o produto NÃO existir (caixa vazia), o .orElse entra em ação:
		        // Ele constrói uma resposta de "Não Encontrado" (Status 404).
		        .orElse(ResponseEntity.notFound().build());
    }

    /**
     * O @PostMapping indica que este método recebe dados.
     * O @RequestBody "converte" o JSON que o usuário envia para um objeto Produto.
     */
    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto) {
        Produto novoProduto = repository.save(produto);
        return ResponseEntity.status(201).body(novoProduto);
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