package com.sistema.delivery.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CategoriaDTO {

    private Integer id;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio!")
    @Length(min = 5, max = 80, message = "Minino de palavras 5 e máximo 80")
    private String nome;
    
}
