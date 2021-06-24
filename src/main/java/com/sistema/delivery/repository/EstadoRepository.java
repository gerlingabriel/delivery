package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Estado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    
}
