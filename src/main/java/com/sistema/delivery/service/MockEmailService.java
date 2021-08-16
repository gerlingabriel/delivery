package com.sistema.delivery.service;

import javax.mail.internet.MimeMessage;

import com.sistema.delivery.domian.Cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstratcEmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOG.info("Simulando envio de Email ... ");
        LOG.info(message.toString());
        LOG.info("Email enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Envio de Email HTML... ");
        LOG.info(msg.toString());
        LOG.info("Email enviado");
        
    }

    @Override
    public void envioDeEmailEsquecido(Cliente cliente, String newPass) {
        LOG.info("Recuperando email.. ");
        LOG.info(newPass.toString());
        LOG.info("Email enviado");
    }

    
}
