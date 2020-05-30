package com.example.demo.Controller.Api;

import com.example.demo.Config.ApiController;
import com.example.demo.Config.HibernateService;
import com.example.demo.Model.User;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/user")
public class ApiHomeController extends ApiController {

    @GetMapping
    @ResponseBody
    protected String getView(Model model) {
        Transaction t = this.getJdbcSession().beginTransaction();
        ArrayList<User> users = (ArrayList<User>) this.getJdbcSession().createQuery("from user").list();
        HibernateService.hibernateService.closeSession(t);
        return super.returnJson(true, users, "");
    }

    @PostMapping
    @ResponseBody
    protected String postView(Model model, User user) {
        Transaction t = this.getJdbcSession().beginTransaction();
        if (user != null && !user.getEmail().equals("")) {
            getJdbcSession().saveOrUpdate(user);
            getJdbcSession().flush();
        }
        HibernateService.hibernateService.closeSession(t);
        return super.returnJson(true, user, "");
    }
}
