package com.sistema.delivery.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ControllerAdvice
public class RecourceHandler {

    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<MsgErro> idNotFound(IdNotFound e, HttpServletRequest request) {
        MsgErro err = new MsgErro(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataInntergratyException.class)
    public ResponseEntity<MsgErro> doNotDeleteCategoryWithproduct(DataInntergratyException e,
            HttpServletRequest request) {
        MsgErro err = new MsgErro(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MsgErro> validationCamp(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidadeError err = new ValidadeError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", LocalDateTime.now());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            err.adderror(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    /**
     * Método paa pegar erros criado
     */
    @Setter
    @Getter
    @AllArgsConstructor
    class MsgErro {
        private Integer status;
        private String msg;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        private LocalDateTime timestamp;
    }

    /**
     * Class para colocar nome do erro do Valid
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CampError {

        private String codeErro;
        private String messegeErro;

    }

    /**
     * Metodo para pegar a lsita de erros do @Valid
     */
    @Getter
    public class ValidadeError extends MsgErro {

        private List<CampError> list = new ArrayList<>(); // imporante instaciar a lista não for feito isto antes

        public ValidadeError(Integer status, String msg, LocalDateTime timestamp) {
            super(status, msg, timestamp);
        }

        public void adderror(String codeErro, String messegeErro) {
            list.add(new CampError(codeErro, messegeErro));
        }

    }
}
