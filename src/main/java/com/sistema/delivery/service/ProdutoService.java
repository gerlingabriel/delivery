package com.sistema.delivery.service;

import java.util.List;

import com.sistema.delivery.domian.Produto;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoService {

    private final ProdutoRepository repository;

    public Produto findById(Integer id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new IdNotFound("Usuário não encontrado"));
        return produto;
    }

    public Produto create(Produto produto) {
        if (produto.getId() != null) {
            findById(produto.getId());
        }
        return repository.save(produto);
    }

    public void deleteId(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    
}
