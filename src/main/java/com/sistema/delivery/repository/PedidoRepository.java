package com.sistema.delivery.repository;

import com.sistema.delivery.domian.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
