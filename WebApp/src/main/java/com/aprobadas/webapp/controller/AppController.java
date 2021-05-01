package com.aprobadas.webapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AppController {

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }
}
