package com.sistema.delivery.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class LoginDTO implements Serializable{

    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
