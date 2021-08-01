package com.sistema.delivery.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ClienteNewDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio!")
    @Length(min = 5, max = 80, message = "Minino de palavras 5 e máximo 80")
    private String nome;

    @Email
    private String email;

    @NotEmpty(message = "CPF/CNPJ não pode ser nulo ou vazio!")
    @Length(min = 11, max = 20, message = "Favor verificar o campo!")
    private String cpfOuCnpj;

    private Integer tipoCliente;
    
    private List<EnderecoDTO> enderecos;

    // Telefones
    private Set<String> telefones;

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

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    

}
