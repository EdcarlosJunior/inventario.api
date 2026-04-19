package com.gestao.inventario_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.inventario_api.model.Produto;

/**
 * Ao estender JpaRepository, você ganha automaticamente os métodos:
 * save(), findAll(), findById(), delete(), etc.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Não precisa escrever nada aqui por enquanto!
}