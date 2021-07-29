package com.sistema.delivery.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.Length;

public class ProdutoDTO implements Serializable{

    private Integer id;

    @NotEmpty(message = "Nome não pode ser vazio ou nulo!")
    @Length(max = 80, min = 5, message = "Nome deve conter")
    private String nome;

    @NotNull
    private Double preco;

    @NotNull
    @JsonIgnoreProperties("produtos")
    private List<CategoriaDTO> categorias;

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    
    
}
