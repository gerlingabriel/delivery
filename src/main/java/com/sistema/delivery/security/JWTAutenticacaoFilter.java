package com.sistema.delivery.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.delivery.dto.CredenciasDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAutenticacaoFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAutenticacaoFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            CredenciasDTO dto =  new ObjectMapper().readValue(request.getInputStream(), CredenciasDTO.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha(), new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(token);

            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
 
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String username = ((UsuarioLogin) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.gerandoJWTToken(username);
        response.addHeader("Authorization", "Bearer " + token);
            
    }
    
}
