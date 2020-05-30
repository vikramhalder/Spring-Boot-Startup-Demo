package com.example.demo.Config;

import com.example.demo.Config.HibernateService;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class ApiController {

    protected Session getJdbcSession() {
        if (HibernateService.hibernateService == null) {
            HibernateService.hibernateService = new HibernateService();
        }
        return HibernateService.hibernateService.getSession();
    }

    @ResponseBody
    protected String returnJson(boolean status, Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("data", data);
        map.put("message", message);
        return new Gson().toJson(map);
    }
}
