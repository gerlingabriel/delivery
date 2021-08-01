package com.sistema.delivery.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistema.delivery.domian.Cliente;
import com.sistema.delivery.domian.Endereco;
import com.sistema.delivery.dto.ClienteDTO;
import com.sistema.delivery.dto.ClienteNewDTO;
import com.sistema.delivery.enums.TipoCliente;
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


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public ClienteDTO findById(Integer id){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new IdNotFound(Cliente.class.getSimpleName() + " não encontrado!"));  
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public ClienteDTO create(ClienteNewDTO clienteNewDTO) {
        if (clienteNewDTO.getId() != null) {
            findById(clienteNewDTO.getId());
        }
        Cliente cliente = converterClienteDTOEmCleinte(clienteNewDTO);  
        return modelMapper.map(repository.save(cliente), ClienteDTO.class);
    }

    private Cliente converterClienteDTOEmCleinte(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = new Cliente(clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipoCliente()), clienteNewDTO.getTelefones());
        Endereco enderecoNovo = new Endereco();
        enderecoNovo.setLogradouro(clienteNewDTO.getEnderecos().get(0).getLogradouro()); 
        enderecoNovo.setNumero(clienteNewDTO.getEnderecos().get(0).getNumero()); 
        enderecoNovo.setComplemento(clienteNewDTO.getEnderecos().get(0).getComplemento());
        enderecoNovo.setBairro( clienteNewDTO.getEnderecos().get(0).getBairro() );
        enderecoNovo.setCep(clienteNewDTO.getEnderecos().get(0).getCep()); 
        enderecoNovo.setCidade(clienteNewDTO.getEnderecos().get(0).getCidadeId());
        enderecoNovo.setCliente(cliente); 
        
        cliente.getEnderecos().add(enderecoNovo);
        return cliente;
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