package com.sistema.delivery.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "pessoa Jurídica");

    private int cod;
    private String descricao;

    public static TipoCliente toEnum(Integer cod){
        
        for(TipoCliente x: TipoCliente.values()){
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inváldio :" +cod);
    }
}
