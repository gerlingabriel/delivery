package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepositpry extends JpaRepository<Endereco, Long> {
    
}
