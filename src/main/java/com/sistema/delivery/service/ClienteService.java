package com.sistema.delivery.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistema.delivery.domian.Cliente;
import com.sistema.delivery.dto.ClienteDTO;
import com.sistema.delivery.dto.ClienteNewDTO;
import com.sistema.delivery.exception.DataInntergratyException;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.ClienteRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

    private final ClienteRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    public ClienteDTO findById(Integer id){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new IdNotFound(Cliente.class.getSimpleName() + " não encontrado!"));  
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public ClienteDTO create(ClienteNewDTO clienteNewDTO) {
        if (clienteNewDTO.getId() != null) {
            findById(clienteNewDTO.getId());
        }
        Cliente cliente = modelMapper.map(clienteNewDTO, Cliente.class);
        return modelMapper.map(repository.save(cliente), ClienteDTO.class);
    }

    public void deleteId(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataInntergratyException("Cliente ainda tem endereço cadastrado!");
        }
        
    }

    public List<ClienteDTO> findAll() {
        return repository.findAll().stream()
                                    .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                                    .collect(Collectors.toList());
    }

    public Page<ClienteDTO> findAllPage(int page, int size, String direction,String orderBy){
        PageRequest pageRequest;
        if (direction.equals("ASC")) {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, orderBy));
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        }
        return repository.findAll(pageRequest).map(cliente -> modelMapper.map(cliente, ClienteDTO.class));
    }

    
    
}