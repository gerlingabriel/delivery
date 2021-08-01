package com.sistema.delivery.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio!")
    @Length(min = 5, max = 80, message = "Minino de palavras 5 e máximo 80")
    private String nome;

    @JsonIgnoreProperties("categorias")
    private List<ProdutoDTO> produtos;

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

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public CategoriaDTO(Integer id,
            @NotEmpty(message = "Nome não pode ser nulo ou vazio!") @Length(min = 5, max = 80, message = "Minino de palavras 5 e máximo 80") String nome,
            List<ProdutoDTO> produtos) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
    }

    public CategoriaDTO() {
    }

    
    
    
}
