package com.sistema.delivery.security;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

    @Value("${jwt.secredo}")
    private String secredo;

    @Value("${jwt.expiracao}")
    private String expiracao;

    public String gerandoJWTToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(5).toInstant()) )
                .signWith(SignatureAlgorithm.HS512, secredo.getBytes())
                .compact();
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            String username = claims.getSubject();
            Date dataExparacao = claims.getExpiration();
            Date dataDeHoje = new Date(System.currentTimeMillis());
            
            if (username != null && dataExparacao != null && dataDeHoje.before(dataExparacao)) {
                return true;
            }
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }


    // Métodos privados
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secredo.getBytes()).parseClaimsJws(token).getBody() ;
        } catch (Exception e) {
            return null;
        }
    }


    
}
