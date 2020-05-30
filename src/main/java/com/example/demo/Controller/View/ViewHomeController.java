package com.example.demo.Controller.View;

import com.example.demo.Config.ViewController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewHomeController extends ViewController {
    @RequestMapping(path = "/")
    String home(Model model) {
        model.addAttribute("msg", "Hello there, This is home page");
        return "home";
    }
}
