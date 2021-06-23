package com.sistema.delivery.domian;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class PagamentoComCartao extends Pagamento {

    private Integer numerosDeparcelas;
    
}
