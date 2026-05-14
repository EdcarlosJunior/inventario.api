package com.gestao.inventario_api.dto;

// Note que aqui usamos a palavra 'record'
public record ProdutoRequestDTO(String nome, Double preco, Integer quantidade) {
}