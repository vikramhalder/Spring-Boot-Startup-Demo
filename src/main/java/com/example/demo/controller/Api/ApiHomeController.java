package com.example.demo.controller.Api;

import com.example.demo.controller.ApiController;
import com.example.demo.data.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
public class ApiHomeController extends ApiController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    @ResponseBody
    protected String getView(Model model) {
        return super.returnJson(true, userRepository.all("user"), "Success");
    }

    @PostMapping
    @ResponseBody
    protected String postView(Model model, User user) {
        userRepository.save(user);
        return super.returnJson(true, user, "Success");
    }
}
