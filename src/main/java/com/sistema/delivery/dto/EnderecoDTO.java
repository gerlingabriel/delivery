package com.sistema.delivery.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistema.delivery.domian.Cidade;

import org.hibernate.validator.constraints.Length;

public class EnderecoDTO {

     // Endereço
    private Long id;

     @NotEmpty(message = "Rua não pode ser nulo ou vazio!")
     @Length(min = 5, max = 80, message = "Minino de palavras 5 e máximo 80")
     private String logradouro;
 
     @NotEmpty(message = "Nome não pode ser nulo ou vazio!")
     private int numero;
 
     private String complemento;
 
     @NotEmpty(message = "Bairro não pode ser nulo ou vazio!")
     @Length(min = 3, max = 80, message = "Minino de palavras 5 e máximo 80")
     private String bairro;
 
     @NotEmpty(message = "Cep não pode ser nulo ou vazio!")
     @Length(min = 8, max = 11, message = "Minino de palavras 5 e máximo 80")
     private String cep;
 
     // tabela cliente do endereco 
     @NotEmpty(message = "Clinete não pode ser nulo ou vazio!")
     @JsonBackReference// entidade  que vai ser consultada (parte fraca)
     private ClienteDTO cliente;

    // Tabela Cidade
    private Cidade cidadeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Cidade cidadeId) {
        this.cidadeId = cidadeId;
    }

    
 
    
}
