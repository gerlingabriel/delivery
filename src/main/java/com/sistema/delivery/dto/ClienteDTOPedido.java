package com.sistema.delivery.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ClienteDTOPedido implements Serializable {
    
    private Integer id;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio!")
    @Length(min = 5, max = 80, message = "Minino de palavras 5 e máximo 80")
    private String nome;

    @Email(message = "E-mail inveálido")
    private String email;

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

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    
}
