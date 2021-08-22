package com.project.nosql.service;

import java.util.List;
import java.util.stream.Collectors;

import com.project.nosql.dto.UserRespDTO;
import com.project.nosql.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;    

    public List<UserRespDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(user -> new UserRespDTO(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
