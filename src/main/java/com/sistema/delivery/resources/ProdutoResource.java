package com.sistema.delivery.resources;

import java.util.List;

import com.sistema.delivery.dto.ProdutoDTO;
import com.sistema.delivery.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
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


@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO produto){
        return ResponseEntity.ok().body(service.create(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> find(@PathVariable Integer id){
        ProdutoDTO produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProdutoDTO produto){
        service.create(produto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.deleteId(id);
    }

    @GetMapping
    @CachePut("listaTodos")
    public ResponseEntity <List<ProdutoDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    @CachePut("listaTodoPorPagina")
    public ResponseEntity <Page<ProdutoDTO>> findAllPage(
                @RequestParam(value = "nomeDoProduto", defaultValue = "") String nomeDoProduto,
                @RequestParam(value = "categoria", defaultValue = "") String categoria,
                @RequestParam(value = "page", defaultValue = "0") int page, 
                @RequestParam(value = "size", defaultValue = "24") int size, 
                @RequestParam(value = "direction", defaultValue = "ASC") String direction, // ou DESC
                @RequestParam(value = "orderBy", defaultValue = "id" ) String orderBy) 
                {
        return ResponseEntity.ok().body(service.findAllPage(page, size, direction, orderBy, nomeDoProduto, categoria));
    }

    
}
