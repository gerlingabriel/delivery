package com.sistema.delivery.domian;

import javax.persistence.Embedded;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ItemPedido {

    @Id
    @Embedded
    private ItemPedidoPk id = new ItemPedidoPk();
    private Double desconto;
    private Integer quantidade;
    private double preco;
    
}
