package com.sistema.delivery.enums;

public enum EstadoPagamento {

    PENDENTE("Pendente"), 
    QUITADO("Quitado"),
    CANCELADO("Cancelado");

    private EstadoPagamento(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

}
