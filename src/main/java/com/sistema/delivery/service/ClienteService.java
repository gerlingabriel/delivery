package com.sistema.delivery.service;

import java.util.List;

import com.sistema.delivery.domian.Cliente;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente findById(Integer id){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new IdNotFound("Categoria não encontrado!"));  
        return cliente;
    }

    public Cliente create(Cliente produto) {
        if (produto.getId() != null) {
            findById(produto.getId());
        }
        return repository.save(produto);
    }

    public void deleteId(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }


    
    
}