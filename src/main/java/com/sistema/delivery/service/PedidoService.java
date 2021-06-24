package com.sistema.delivery.service;

import java.util.List;

import com.sistema.delivery.domian.Pedido;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoService {

    private final PedidoRepository repository;

    public Pedido findById(Integer id){
        Pedido pedido = repository.findById(id).orElseThrow(() -> new IdNotFound("Categoria não encontrado!"));  
        return pedido;
    }

    public Pedido create(Pedido pedido) {
        if (pedido.getId() != null) {
            findById(pedido.getId());
        }
        return repository.save(pedido);
    }

    public void deleteId(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
    
    public List<Pedido> findAll() {
        return repository.findAll();
    }


}
