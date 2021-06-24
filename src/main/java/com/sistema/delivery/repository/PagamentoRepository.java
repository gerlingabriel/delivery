package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    
}
