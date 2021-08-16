package com.sistema.delivery.service;

import com.sistema.delivery.security.UsuarioLogin;

import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioLogado {

    public static UsuarioLogin emailDoLogin (){
        try {
            return (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
    
}
