package com.example.demo.Config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateService {
    public static HibernateService hibernateService;
    static SessionFactory sessionFactory;
    static Session session;

    public Session getSession() {
        if (session != null && session.isConnected()) {
            return session;
        }
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        return session;
    }

    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    public void closeSession(Transaction t) {
        t.commit();
        closeSession();
    }
}
