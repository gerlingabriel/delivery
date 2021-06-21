package com.sistema.delivery.service;

import com.sistema.delivery.domian.Categoria;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria buscar(Long id){
        Categoria categoria = new Categoria();
        try {
            // Buscar categoria por id - caso não ache irá lançar exeption
            categoria = categoriaRepository.findById(id).orElseThrow(() -> new IdNotFound("Categoria não encontrado!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }
    
}
