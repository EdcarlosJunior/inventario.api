package com.gestao.inventario_api.model;

// Importações necessárias para mapear a classe ao banco de dados MySQL
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A anotação @Entity define que esta classe é uma entidade do banco de dados.
 * O Hibernate usará esta classe para criar a tabela automaticamente.
 * A anotação @Table define o nome da tabela que será criada no MySQL.
 */
@Entity
@Table(name = "produtos")
public class Produto {

    /**
     * @Id indica que este campo é a Chave Primária (Primary Key) da tabela.
     * @GeneratedValue define a estratégia de geração do ID. 
     * GenerationType.IDENTITY é a forma correta para o "AUTO_INCREMENT" do MySQL.
     * Alterei para 'Long' por ser o padrão recomendado em projetos Spring,
     * pois permite valores nulos antes do objeto ser persistido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private double preco;
    
    private int quantidade;

    /**
     * Construtor Vazio:
     * É OBRIGATÓRIO para o JPA/Hibernate. Ele usa esse construtor 
     * para criar instâncias da classe ao buscar dados no banco.
     */
    public Produto() {
    }

    /**
     * Construtor Completo:
     * Útil para criarmos objetos rapidamente no código para salvar no banco.
     */
    public Produto(Long id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    /**
     * Getters e Setters:
     * O Spring Boot usa esses métodos para ler e gravar os dados nos campos privados.
     * Sem eles, sua API não conseguirá mostrar os dados no formato JSON.
     */
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}