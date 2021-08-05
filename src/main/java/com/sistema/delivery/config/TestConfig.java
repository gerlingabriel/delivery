package com.sistema.delivery.config;

import com.sistema.delivery.service.EmailService;
import com.sistema.delivery.service.StmpEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService(){
        /**
         * O certo é ter dois perfis, mas como outro banco está vazio então só alterei aqui
         */
       // return new MockEmailService();
       return new StmpEmailService();
    }
    
}
