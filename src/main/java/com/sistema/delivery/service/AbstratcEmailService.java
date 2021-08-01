package com.sistema.delivery.service;

import com.sistema.delivery.domian.Pedido;

import org.springframework.mail.SimpleMailMessage;

public abstract class AbstratcEmailService implements EmailService {

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido){
        SimpleMailMessage simpleMailMessage = prepararEmailParaEnvio(pedido);
        sendEmail(simpleMailMessage);
    }

    private SimpleMailMessage prepararEmailParaEnvio(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        // sm.setFrom();
        return null;
    }
    
}
