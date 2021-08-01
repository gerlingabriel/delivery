package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
