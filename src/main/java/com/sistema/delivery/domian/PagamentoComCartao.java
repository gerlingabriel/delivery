package com.sistema.delivery.domian;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pcc")
public class PagamentoComCartao extends Pagamento {

    private Integer numerosDeparcelas;

    public Integer getNumerosDeparcelas() {
        return numerosDeparcelas;
    }

    public void setNumerosDeparcelas(Integer numerosDeparcelas) {
        this.numerosDeparcelas = numerosDeparcelas;
    }

    
    
}
