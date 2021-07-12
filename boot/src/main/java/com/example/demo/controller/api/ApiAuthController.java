package com.example.demo.controller.api;

import com.example.demo.core.iservice.ApiController;
import com.example.demo.core.service.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class ApiAuthController extends ApiController {
    @Autowired
    UserService userService;

    @GetMapping
    protected String getView(Model model, User user) {
        if (user.getUsername() != null && user.getUsername().length() > 2) {
            userService.save(user);
        }
        return json(true, userService.findAll(), "Success");
    }

    @PostMapping
    protected String postView(Model model, User user) {
        userService.save(user);
        return json(true, user, "Success");
    }
}
