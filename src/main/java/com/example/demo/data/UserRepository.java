package com.example.demo.data;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserRepository extends AbstractRepository {

    public List<User> all(String table) {
        Query q = entityManager.createQuery("from " + table);
        return (List<User>) q.getResultList();
    }
}