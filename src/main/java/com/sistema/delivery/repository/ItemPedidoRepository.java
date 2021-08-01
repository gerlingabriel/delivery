package com.sistema.delivery.repository;

import com.sistema.delivery.domian.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    
}
