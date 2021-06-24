package com.sistema.delivery.service;

import java.util.List;

import com.sistema.delivery.domian.Categoria;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria findById(Integer id){
        Categoria categoria = repository.findById(id).orElseThrow(() -> new IdNotFound("Categoria não encontrado!"));  
        return categoria;
    }

    public Categoria create(Categoria categoria) {
        if (categoria.getId() != null) {
            findById(categoria.getId());
        }
        return repository.save(categoria);
    }

    public void deleteId(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    
}
