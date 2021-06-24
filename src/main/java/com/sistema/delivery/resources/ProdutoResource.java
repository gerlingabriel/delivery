package com.sistema.delivery.resources;

import java.util.List;

import com.sistema.delivery.domian.Produto;
import com.sistema.delivery.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoResource {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        return ResponseEntity.ok().body(service.create(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Produto produto){
        service.create(produto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.deleteId(id);
    }

    @GetMapping
    public ResponseEntity <List<Produto>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    
}
