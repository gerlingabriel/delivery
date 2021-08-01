package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
