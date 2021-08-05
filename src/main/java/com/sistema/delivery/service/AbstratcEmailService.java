package com.sistema.delivery.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.sistema.delivery.domian.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstratcEmailService implements EmailService {

    @Value("${email.enviar}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;  

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido){
        SimpleMailMessage simpleMailMessage = prepararEmailParaEnvio(pedido);
        sendEmail(simpleMailMessage);
    }

    private SimpleMailMessage prepararEmailParaEnvio(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado: " +pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        return sm;
    }

    // Criando métodod para pegar HTML e praprar para envio
    protected String htmlFromTemplatePedido(Pedido obj){
        Context context = new Context();
        context.setVariable("pedido", obj);
        return templateEngine.process("email/confirmaçãoEmai", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido){
        MimeMessage simpleMailMessage = null;
        try {
            simpleMailMessage = prepareMimeEmailParaEnvio(pedido);
        } catch (MessagingException e) {
            e.printStackTrace();
            sendOrderConfirmationEmail(pedido);
        }
        //Enviando esse arquivo para as configuraçẽos no propeties
        sendHtmlEmail(simpleMailMessage);
    }

    protected MimeMessage prepareMimeEmailParaEnvio(Pedido pedido) throws MessagingException {
        MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true);
        helper.setTo(pedido.getCliente().getEmail());
        helper.setFrom(sender);
        helper.setSubject("Pedido confirmado! Código: " + pedido.getId());
        helper.setSentDate(new Date(System.currentTimeMillis()));
        helper.setText(htmlFromTemplatePedido(pedido), true);
        return simpleMailMessage;
    }
    
}
