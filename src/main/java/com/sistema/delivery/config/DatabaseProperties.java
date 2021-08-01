package com.sistema.delivery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
public class DatabaseProperties {
	
    private String enviar;
    private String receber;
    
    public String getEnviar() {
        return enviar;
    }
    public void setEnviar(String enviar) {
        this.enviar = enviar;
    }
    public String getReceber() {
        return receber;
    }
    public void setReceber(String receber) {
        this.receber = receber;
    }
    
    
    
}
