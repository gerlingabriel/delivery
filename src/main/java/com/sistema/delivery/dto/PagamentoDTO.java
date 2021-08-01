package com.sistema.delivery.dto;

import java.io.Serializable;

import com.sistema.delivery.enums.EstadoPagamento;

import lombok.Data;

@Data
public class PagamentoDTO implements Serializable {

    private EstadoPagamento status;

    public EstadoPagamento getStatus() {
        return status;
    }

    public void setStatus(EstadoPagamento status) {
        this.status = status;
    }

}
