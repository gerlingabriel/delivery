package com.sistema.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IdNotFound extends RuntimeException {

    public IdNotFound(String msg){
        super(msg);
    }
    
}
