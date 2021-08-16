package com.sistema.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AcessoAUsuarioNegado extends RuntimeException {

    public AcessoAUsuarioNegado(String msg){
        super(msg);
    }
    public AcessoAUsuarioNegado(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
