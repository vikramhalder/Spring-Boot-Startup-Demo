package com.example.demo.controller.view;

import com.example.demo.core.service.UserService;
import com.example.demo.entity.PrincipalUser;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewHomeController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/")
    String home(Authentication authentication, Model model) {
        model.addAttribute("msg", String.format("Hello %s, This is home page", ((PrincipalUser) authentication.getPrincipal()).getName()));
        return "home";
    }

    @RequestMapping(path = "/login")
    String login() {
        return "login";
    }

    @RequestMapping(path = "/join")
    String join(User user) {
        if (user.getUsername() != null && user.getUsername().length() > 2) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.save(user);
        }
        return "join";
    }
}
