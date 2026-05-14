package com.gestao.inventario_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotNull;

/**
 * A anotação @Entity define que esta classe é uma entidade do banco de dados. [cite: 3]
 * O Hibernate usará esta classe para criar a tabela automaticamente. [cite: 4]
 */
@Entity
@Table(name = "produtos")
public class Produto {

    /**
     * @Id indica que este campo é a Chave Primária (Primary Key) da tabela. [cite: 7]
     * @GeneratedValue define a estratégia de geração do ID (AUTO_INCREMENT no MySQL). [cite: 7]
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Agora o ID está corretamente anotado e no topo [cite: 8, 12]

    @NotBlank(message = "O nome do produto não pode estar vazio")
    private String nome;

    @NotNull(message = "O preço é obrigatório")
    @PositiveOrZero(message = "O preço deve ser maior ou igual a zero")
    private Double preco; // Usando Double para suportar validações @NotNull [cite: 10]

    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    private Integer quantidade; // Usando Integer para suportar validações @NotNull [cite: 11]

    /**
     * Construtor Vazio: OBRIGATÓRIO para o JPA/Hibernate. [cite: 13, 14]
     */
    public Produto() {
    }

    /**
     * Construtor Completo: Útil para criar objetos rapidamente. [cite: 15]
     */
    public Produto(Long id, String nome, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters: Essenciais para que o Spring converta para JSON [cite: 17, 18]
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Mude de int para Integer
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}