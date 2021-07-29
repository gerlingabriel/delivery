package com.sistema.delivery.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sistema.delivery.domian.ItemPedido;
import com.sistema.delivery.domian.PagamentoComBoleto;
import com.sistema.delivery.domian.Pedido;
import com.sistema.delivery.domian.Produto;
import com.sistema.delivery.dto.ClienteDTOPedido;
import com.sistema.delivery.dto.EnderecoDTO;
import com.sistema.delivery.dto.ItemPedidoDTO;
import com.sistema.delivery.dto.PedidoDTO;
import com.sistema.delivery.dto.PedidoDTOResp;
import com.sistema.delivery.dto.ProdutoDTO;
import com.sistema.delivery.enums.EstadoPagamento;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.PedidoRepository;
import com.sistema.delivery.repository.ProdutoRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public PedidoDTOResp findById(Integer id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new IdNotFound(Pedido.class.getSimpleName() + " não encontrado!"));
        return conversaoPedidoParaPedidoDTOResp(pedido);
    }

    public PedidoDTOResp create(PedidoDTO pedidoDTO) {
        Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);

        if (pedido.getId() != null) {
            // Caso para atualização
            findById(pedido.getId());
        } else {
            pedido.setInstante(new Date());
            pedido.getPagamento().setStatus(EstadoPagamento.PENDENTE);
            pedido.getPagamento().setPedido(pedido);

            verificarPagamento(pedido);

            for (ItemPedido item : pedido.getItens()) {
                Produto produto = produtoRepository.findById(item.getId().getProduto().getId()).get();
                item.setDesconto(0.0);
                item.setPreco(produto.getPreco());
                item.getId().setPedido(pedido);
            }

        }
        repository.save(pedido);
        return conversaoPedidoParaPedidoDTOResp(pedido);
    }

    public void deleteId(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<PedidoDTOResp> findAll() {
        modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.NONE);
        return repository.findAll().stream().map(this::conversaoPedidoParaPedidoDTOResp).collect(Collectors.toList());
    }

    private void verificarPagamento(Pedido pedido) {
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pgto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.criarBoleto(pedido, pgto);
        }
    }

    private PedidoDTOResp conversaoPedidoParaPedidoDTOResp(Pedido pedido) {

        PedidoDTOResp response = new PedidoDTOResp();

        response.setId(pedido.getId());
        response.setInstante(pedido.getInstante());
        EstadoPagamento statusAux = pedido.getPagamento().getStatus();
        response.getPagamento().setStatus(statusAux);
        response.setCliente(modelMapper.map(pedido.getCliente(), ClienteDTOPedido.class));
        response.setEnderecoDeEntrega(modelMapper.map(pedido.getEnderecoDeEntrega(), EnderecoDTO.class));
        List<ItemPedidoDTO> lista = pedido.getItens().stream()
                    .map(this::conversarItem)
                    .collect(Collectors.toList());
        response.setItens(lista);

        return response;
    }

    private ItemPedidoDTO conversarItem(ItemPedido itemPedido){
        ProdutoDTO produto = modelMapper.map(itemPedido.getId().getProduto(), ProdutoDTO.class);
        ItemPedidoDTO dto = new ItemPedidoDTO(null, produto, itemPedido.getDesconto(), itemPedido.getQuantidade(), itemPedido.getPreco());
        return dto;
    }
}
