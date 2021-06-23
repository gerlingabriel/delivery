package com.sistema.delivery.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

    PENDENTE("Pendente"), 
    QUITADO("Quitado"),
    CANCELADO("Cancelado");

    private String status;

}
