package com.sistema.delivery.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistema.delivery.domian.Categoria;
import com.sistema.delivery.dto.CategoriaDTO;
import com.sistema.delivery.exception.DataInntergratyException;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.CategoriaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public CategoriaDTO findById(Integer id){
        Categoria categoria = repository.findById(id).orElseThrow(() -> new IdNotFound(Categoria.class.getSimpleName() +" não encontrado!"));  
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    public CategoriaDTO create(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getId() != null) {
            findById(categoriaDTO.getId());
        }
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        return modelMapper.map(repository.save(categoria), CategoriaDTO.class);
    }

    public void deleteId(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataInntergratyException("Categoria ainda possui Produto cadastrado(s)!");
        }
        
    }

    public List<CategoriaDTO> findAll() {
        return repository.findAll().stream()
                                    .map(categoria -> modelMapper.map(categoria, CategoriaDTO.class))
                                    .collect(Collectors.toList());
    }

    public Page<CategoriaDTO> findAllPage(int page, int size, String direction,String orderBy){
        PageRequest pageRequest;
        if (direction.equals("ASC")) {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, orderBy));
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        }
        return repository.findAll(pageRequest).map(categoria -> modelMapper.map(categoria, CategoriaDTO.class));
    }

    
}
