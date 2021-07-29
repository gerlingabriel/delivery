package com.sistema.delivery.resources;

import java.net.URI;
import java.util.List;

import com.sistema.delivery.dto.PedidoDTO;
import com.sistema.delivery.dto.PedidoDTOResp;
import com.sistema.delivery.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;    

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTOResp> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDTOResp> create(@RequestBody PedidoDTO pedidoDTO){
        PedidoDTOResp novoPedido = service.create(pedidoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // @PutMapping
    // public ResponseEntity<Void> update(@RequestBody Pedido pedido){
    //     service.create(pedido);
    //     return ResponseEntity.noContent().build();
    // }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.deleteId(id);
    }

    @GetMapping
    public ResponseEntity <List<PedidoDTOResp>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


    
}
