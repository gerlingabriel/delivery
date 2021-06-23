package com.sistema.delivery.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class RecourceHandler {

    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<MsgErro> idNotFound(IdNotFound e, HttpServletRequest request){
        MsgErro err = new MsgErro(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    


    @Setter
    @Getter
    @AllArgsConstructor
    class MsgErro {
        private Integer status;
        private String msg;
        private Long timestamp;
    }
}
