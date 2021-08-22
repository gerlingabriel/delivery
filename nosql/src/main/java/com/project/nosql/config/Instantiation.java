package com.project.nosql.config;

import java.util.Arrays;

import com.project.nosql.model.User;
import com.project.nosql.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner{

    @Autowired
    private UserRepository repository;

    // Instaciar algumas class para testar
    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        User gabriel = new User(null, "Gabriel Gerlin Dias", "gerlin.gabriel@gamil.com");
        User rafael = new User(null, "Rafafel Gerlin Dias", "gerlinadldinho@hotmail.com");
        User barbara = new User(null, "Bárbara Cristino Glinardello Melo", "binhaglinarddello@gmail.com");

        repository.saveAll(Arrays.asList(gabriel, rafael, barbara));
        
    }
    
}
