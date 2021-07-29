package com.sistema.delivery.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PedidoDTOResp {

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date instante;

    private PagamentoDTO pagamento = new PagamentoDTO();

    private ClienteDTOPedido cliente;

    private EnderecoDTO enderecoDeEntrega;

    @JsonIgnoreProperties("produto , pedido")
    private List<ItemPedidoDTO> itens = new ArrayList<>();

    public double getSomaTotal(){
        return itens.stream()
                .map(x -> x.getSubPedido())
                .reduce(0.0, (a, b) -> a + b);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public PagamentoDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDTO pagamento) {
        this.pagamento = pagamento;
    }

    public ClienteDTOPedido getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTOPedido cliente) {
        this.cliente = cliente;
    }

    public EnderecoDTO getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(EnderecoDTO enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    
    
}