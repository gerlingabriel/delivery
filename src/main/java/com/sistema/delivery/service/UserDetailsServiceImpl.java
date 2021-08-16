package com.sistema.delivery.service;

import com.sistema.delivery.domian.Cliente;
import com.sistema.delivery.repository.ClienteRepository;
import com.sistema.delivery.security.UsuarioLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente novoCliente = clienteRepository.findByEmail(email);
        
        if (novoCliente == null) {
            throw new UsernameNotFoundException( email + " não encontrado.");
        }

        return new UsuarioLogin(novoCliente.getId(), novoCliente.getEmail(), novoCliente.getSenha(), novoCliente.getPerfis());
    }
    
}
