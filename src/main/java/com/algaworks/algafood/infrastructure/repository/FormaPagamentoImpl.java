package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class FormaPagamentoImpl implements FormaPagamentoRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> todas() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class)
                .getResultList();
    }

    @Override
    @Transactional
    public FormaPagamento adicionar(FormaPagamento formaPagamento){
        return manager.merge(formaPagamento);
    }

    @Override
    public FormaPagamento porId(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public void remover(FormaPagamento formaPagamento){
        formaPagamento = porId(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
