package com.sistema.delivery.domian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistema.delivery.enums.Perfil;
import com.sistema.delivery.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;
    private String senha;

     // Ele poderia ver os endereços
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> perfis = new HashSet<>();

    @JsonBackReference // pedido ira mostra os clientes
    @OneToMany(mappedBy = "cliente") // nome do atributo que mapeou
    private List<Pedido> pedidos;

    public Cliente() {
        addPerfil(Perfil.USER);
    }

    public Cliente(String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente, String senha, Set<String> telefones) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = (tipoCliente == null) ? null: tipoCliente.getCod();
        this.telefones = telefones;
        this.senha = senha;
        addPerfil(Perfil.USER);
    }

    public Set<Perfil> getPerfis() {
        return perfis
                .stream()
                .map(x -> Perfil.toEnum(x))
                .collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }

    // Métodos mais sofisticado para controle de ENUM
    public void setTipoCliente(TipoCliente tipoCliente){
        this.tipoCliente = tipoCliente.getCod();
    }

    public TipoCliente getTipoCliente(){
        return TipoCliente.toEnum(tipoCliente);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
