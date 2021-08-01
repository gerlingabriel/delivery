package com.sistema.delivery.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "pessoa Jurídica");

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

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

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
