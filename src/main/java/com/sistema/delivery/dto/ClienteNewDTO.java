package com.sistema.delivery.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.sistema.delivery.enums.Perfil;

import org.hibernate.validator.constraints.Length;

public class ClienteNewDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio!")
    @Length(min = 5, max = 80, message = "Minino de palavras 5 e máximo 80")
    private String nome;

    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "CPF/CNPJ não pode ser nulo ou vazio!")
    @Length(min = 11, max = 20, message = "Favor verificar o campo!")
    private String cpfOuCnpj;

    private Integer tipoCliente;
    
    private List<EnderecoDTO> enderecos;

    @NotEmpty(message = "Senha não pode ser nulo ou vazia!")
    @Length(min = 3, max = 80, message = "Minino de palavras 3 e máximo 15")
    private String senha;

    private Set<Integer> perfis;

    public Set<Perfil> getPerfis() {
        return perfis
                .stream()
                .map(x -> Perfil.toEnum(x))
                .collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

}
