package com.sistema.delivery.service;

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

    public Cliente buscar(Long id){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new IdNotFound("Categoria não encontrado!"));  
        return cliente;
    }
    
    
}