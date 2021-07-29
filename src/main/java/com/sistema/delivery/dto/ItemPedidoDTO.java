package com.sistema.delivery.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ItemPedidoDTO {

    @JsonIgnore
    private PedidoDTO pedido;
    @JsonIgnoreProperties("pedido")
    private ProdutoDTO produto ;
    private Double desconto;
    private Integer quantidade;
    private double preco;

    public double getSubPedido(){
        return (preco - desconto) *  quantidade;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public ItemPedidoDTO(PedidoDTO pedido, ProdutoDTO produto, Double desconto, Integer quantidade, double preco) {
        this.pedido = pedido;
        this.produto = produto;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPedidoDTO() {
    }

}
