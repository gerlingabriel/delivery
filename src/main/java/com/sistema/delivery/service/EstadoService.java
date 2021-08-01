package com.sistema.delivery.service;

import java.util.List;

import com.sistema.delivery.domian.Estado;
import com.sistema.delivery.exception.DataInntergratyException;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public Estado findById(Integer id) {
        Estado estado = repository.findById(id)
                .orElseThrow(() -> new IdNotFound(Estado.class.getSimpleName() + " não encontrado!"));
        return estado;
    }

    public Estado create(Estado estado) {
        if (estado.getId() != null) {
            findById(estado.getId());
        }
        return repository.save(estado);
    }

    public void deleteId(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataInntergratyException("Estado ainda possui cidade cadastrado(s)!");
        }

    }

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Page<Estado> findAllPage(int page, int size, String direction, String orderBy) {
        PageRequest pageRequest;
        if (direction.equals("ASC")) {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, orderBy));
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        }
        return repository.findAll(pageRequest);
    }

}
