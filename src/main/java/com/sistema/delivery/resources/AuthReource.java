package com.sistema.delivery.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistema.delivery.dto.LoginDTO;
import com.sistema.delivery.security.JWTUtil;
import com.sistema.delivery.security.UsuarioLogin;
import com.sistema.delivery.service.AuthEmail;
import com.sistema.delivery.service.UsuarioLogado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthReource {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthEmail authEmail;

    @PostMapping
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UsuarioLogin usuarioLogin = UsuarioLogado.emailDoLogin();
        String token = jwtUtil.gerandoJWTToken(usuarioLogin.getUsername());

        response.addHeader("Authorization", "Bearer " + token);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/senha")
    public ResponseEntity<Void> recuperarSenha(@Valid @RequestBody LoginDTO loginDTO){

        authEmail.enviarEmailQuandoEsquece(loginDTO.getEmail());

        return ResponseEntity.noContent().build();
    }
    
}