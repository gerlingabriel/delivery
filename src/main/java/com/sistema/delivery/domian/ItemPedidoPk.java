package com.sistema.delivery.domian;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class ItemPedidoPk {
    
    @ManyToOne
    @JoinColumn(name = "")
    private Pedido pedido;
    private Produto produto;
}
