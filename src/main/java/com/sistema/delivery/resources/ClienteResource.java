package com.sistema.delivery.resources;

import java.util.List;

import com.sistema.delivery.dto.ClienteDTO;
import com.sistema.delivery.dto.ClienteNewDTO;
import com.sistema.delivery.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteResource {

    private final ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteNewDTO cliente){
        return ResponseEntity.ok().body(service.create(cliente));

    }

    @PutMapping
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteNewDTO cliente){
        service.create(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.deleteId(id);
    }

    @GetMapping
    public ResponseEntity <List<ClienteDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity <Page<ClienteDTO>> findAllPage(
                @RequestParam(value = "page", defaultValue = "0") int page, 
                @RequestParam(value = "size", defaultValue = "24") int size, 
                @RequestParam(value = "direction", defaultValue = "ASC") String direction, // ou DESC
                @RequestParam(value = "orderBy", defaultValue = "id" ) String orderBy) 
                {
        return ResponseEntity.ok().body(service.findAllPage(page, size, direction, orderBy));
    }

    
}
