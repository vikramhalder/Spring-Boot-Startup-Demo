package com.example.demo.controller.View;

import com.example.demo.core.service.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewHomeController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/")
    String home(Model model) {
        model.addAttribute("msg", "Hello, This is home page");
        return "home";
    }

    @RequestMapping(path = "/login")
    String login() {
        return "login";
    }

    @RequestMapping(path = "/join")
    String join(User user) {
        if (user.getUsername() != null && user.getUsername().length() > 2) {
            user.doPasswordEnc();
            userService.save(user);
        }
        return "join";
    }
}
