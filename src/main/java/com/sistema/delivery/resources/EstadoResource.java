package com.sistema.delivery.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.sistema.delivery.domian.Estado;
import com.sistema.delivery.service.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Estado> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
 
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid Estado estado){
        estado  = service.create(estado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid Estado estado){
        service.create(estado);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.deleteId(id);
    }

    @GetMapping
    public ResponseEntity <List<Estado>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity <Page<Estado>> findAllPage(
                @RequestParam(value = "page", defaultValue = "0") int page, 
                @RequestParam(value = "size", defaultValue = "24") int size, 
                @RequestParam(value = "direction", defaultValue = "ASC") String direction, // ou DESC
                @RequestParam(value = "orderBy", defaultValue = "id" ) String orderBy) 
                {
        return ResponseEntity.ok().body(service.findAllPage(page, size, direction, orderBy));
    }
}
