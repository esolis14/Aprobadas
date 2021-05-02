package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AppController {

    @GetMapping({"/","/home"})
    public String showHome() {
        return "home";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("emailError", false);
        return "login";
    }
}
