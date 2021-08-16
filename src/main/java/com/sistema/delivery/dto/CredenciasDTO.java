package com.sistema.delivery.dto;

import java.io.Serializable;

public class CredenciasDTO implements Serializable{

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
