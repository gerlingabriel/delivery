package com.project.nosql.repository;

import com.project.nosql.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    
}
