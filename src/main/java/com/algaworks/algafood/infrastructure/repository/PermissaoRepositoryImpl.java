package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> todas() {
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Permissao adicionar(Permissao permissao){
        return manager.merge(permissao);
    }

    @Override
    public Permissao porId(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao){
        permissao = porId(permissao.getId());
        manager.remove(permissao);
    }
}
