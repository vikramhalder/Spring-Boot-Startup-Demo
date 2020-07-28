package com.example.demo.data;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class AbstractRepository {
    @PersistenceContext
    public EntityManager entityManager;

    public void save(Object u) {
        entityManager.persist(u);
        entityManager.flush();
        entityManager.clear();
    }

    public void delete(Object u) {
        entityManager.remove(u);
        entityManager.flush();
        entityManager.clear();
    }
}
