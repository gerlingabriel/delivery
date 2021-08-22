package com.project.nosql.recource;

import java.util.List;

import com.project.nosql.dto.UserRespDTO;
import com.project.nosql.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserRespDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
    
}
