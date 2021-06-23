package com.sistema.delivery.service;

import com.sistema.delivery.domian.Produto;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IdNotFound("Usuário não encontrado"));
        return produto;
    }


    
}
