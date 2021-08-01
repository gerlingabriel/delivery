package com.sistema.delivery.service;

import java.util.Calendar;

import com.sistema.delivery.domian.PagamentoComBoleto;
import com.sistema.delivery.domian.Pedido;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {

    public void criarBoleto(Pedido pedido, PagamentoComBoleto pgto) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pedido.getInstante());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        pgto.setDataVencimento(cal.getTime());
    }
    
}
