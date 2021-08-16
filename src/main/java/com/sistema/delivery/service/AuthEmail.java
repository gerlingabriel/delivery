package com.sistema.delivery.service;

import java.util.Random;

import com.sistema.delivery.domian.Cliente;
import com.sistema.delivery.exception.UsuarioNaoExiste;
import com.sistema.delivery.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthEmail {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private BCryptPasswordEncoder password;
    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void enviarEmailQuandoEsquece(String email){
        Cliente cliente = repository.findByEmail(email);
        if (cliente == null) {
            throw new UsuarioNaoExiste("Email não existe!");
        }

        String senhaNova = newPassword();
        cliente.setSenha(password.encode(senhaNova));
        
        repository.save(cliente);
        emailService.envioDeEmailEsquecido(cliente, senhaNova);
    }

    private String newPassword() {
        char[] vet = new char[10];

        for (int i=0; i<10; i++){
            vet[i] = sortearLetras();
        }

        return new String(vet);
    }


    private char sortearLetras() {
        int sorteioLetrasENUmerros = random.nextInt(3);

        if (sorteioLetrasENUmerros == 0) { // será um número
            return (char) (random.nextInt(10)+48);
        } else if (sorteioLetrasENUmerros == 1) {
            return (char) (random.nextInt(26)+65);
        } else {
            return (char) (random.nextInt(26)+97);
        }
    }
    
}
