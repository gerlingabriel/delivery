package com.sistema.delivery.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
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
    
    // Endereço

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
    private Integer cliente;

    // Telefones
    private List<String> telefone;
    
    // Tabela Cidade
    private Integer cidadeId;

}
