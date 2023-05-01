package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> todos() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Estado adicionar(Estado estado){
        return manager.merge(estado);
    }

    @Override
    public Estado porId(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public void remover(Estado estado){
        estado = porId(estado.getId());
        manager.remove(estado);
    }
}
