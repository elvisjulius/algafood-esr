package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


//@Component
public class AtivacaoClienteService {


    @TipoDoNotificador(NivelUrgencia.NORMAL)
    @Autowired
    private Notificador notificador;

//    @PostConstruct
    public void init(){
        System.out.println("INIT");
    }

//    @PreDestroy
    public void destroy(){
        System.out.println("DESTROY");
    }
    public void ativar(Cliente cliente) {
        cliente.ativar();

            notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
    }

}
