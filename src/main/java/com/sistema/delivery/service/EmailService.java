package com.sistema.delivery.service;

import com.sistema.delivery.domian.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);
    
}
