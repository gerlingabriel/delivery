package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
