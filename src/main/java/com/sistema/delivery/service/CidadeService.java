package com.sistema.delivery.service;

import java.util.List;

import com.sistema.delivery.domian.Cidade;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Cidade findById(Integer id) {
        Cidade cidade = repository.findById(id)
                .orElseThrow(() -> new IdNotFound(Cidade.class.getSimpleName() + " não encontrado!"));
        return cidade;
    }

    public Cidade create(Cidade cidade) {
        if (cidade.getId() != null) {
            findById(cidade.getId());
        }
        return repository.save(cidade);
    }

    public void deleteId(Integer id) {
        findById(id);
        repository.deleteById(id);

    }

    public List<Cidade> findAll() {
        return repository.findAll();
    }

    public Page<Cidade> findAllPage(int page, int size, String direction, String orderBy) {
        PageRequest pageRequest;
        if (direction.equals("ASC")) {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, orderBy));
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        }
        return repository.findAll(pageRequest);
    }

}
