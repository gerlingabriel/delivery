package com.sistema.delivery.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonFormat;

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
        MsgErro err = new MsgErro(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    


    @Setter
    @Getter
    @AllArgsConstructor
    class MsgErro {
        private Integer status;
        private String msg;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        private LocalDateTime timestamp;
    }
}
