package com.aprobadas.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/edit")
    public String showStudentProfile() {
        return "edit_profile";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "profile";
    }
}
