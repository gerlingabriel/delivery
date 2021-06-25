package com.sistema.delivery.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ProdutoDTO {

    private Integer id;

    @NotEmpty(message = "Nome não pode ser vazio ou nulo!")
    @Length(max = 80, min = 5, message = "Nome deve conter")
    private String nome;

    @NotNull
    private Double preco;
    
}
