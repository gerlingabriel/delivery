package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Page<Produto> findByNomeContainsIgnoreCase(String nome, Pageable page);

    Page<Produto> findByCategoriasId(int idCategoria, Pageable pageRequest);

    @Transactional(readOnly = true)
    Page<Produto> findByCategoriasIdAndNomeContainsIgnoreCase(int idCategoria, String nomeDoProduto, Pageable pageRequest);

    
}
