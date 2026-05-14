package com.gestao.inventario_api.dto;

import com.gestao.inventario_api.model.Produto;

public record ProdutoResponseDTO(Long id, String nome, Double preco, Integer quantidade) {
    
    // Este construtor facilita transformar a Entidade em DTO no Controller
    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
    }
}