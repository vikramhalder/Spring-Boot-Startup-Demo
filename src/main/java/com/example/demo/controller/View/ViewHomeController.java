package com.example.demo.controller.View;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewHomeController {
    @RequestMapping(path = "/")
    String home(Model model) {
        model.addAttribute("msg", "Hello, This is home page");
        return "home";
    }
}
