package com.sistema.delivery.resources;

import java.util.List;

import com.sistema.delivery.domian.Pedido;
import com.sistema.delivery.service.PedidoService;

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
@RequestMapping("/pedidos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoResource {

    private final PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(service.create(pedido));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Pedido pedido){
        service.create(pedido);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.deleteId(id);
    }

    @GetMapping
    public ResponseEntity <List<Pedido>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


    
}
