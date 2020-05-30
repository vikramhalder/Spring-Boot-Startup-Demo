package com.example.demo.Config;

import com.example.demo.Config.HibernateService;
import org.hibernate.Session;

public class ViewController {
    protected Session getJdbcSession() {
        if (HibernateService.hibernateService == null) {
            HibernateService.hibernateService = new HibernateService();
        }
        return HibernateService.hibernateService.getSession();
    }
}
