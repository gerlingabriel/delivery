package com.sistema.delivery.domian;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistema.delivery.enums.TipoCliente;

import lombok.Data;

@Data
@Entity
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;

    @JsonManagedReference // Ele poderia ver os endereços
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> endereco;

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones;

    @OneToMany(mappedBy = "cliente") // nome do atributo que mapeou
    private List<Pedido> pedidos;

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente,
            List<Endereco> endereco, Set<String> telefones) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente.getCod();
        this.endereco = endereco;
        this.telefones = telefones;
    }

    // Métodos mais sofisticado para controle de ENUM
    public void setTipoCliente(TipoCliente tipoCliente){
        this.tipoCliente = tipoCliente.getCod();
    }

    public TipoCliente getTipoCliente(){
        return TipoCliente.toEnum(tipoCliente);
    }
    
}
