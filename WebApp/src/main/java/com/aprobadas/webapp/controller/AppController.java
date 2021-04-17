package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/teacher")
    public String showTeacherProfile() {
        return "teacher_profile";
    }

    @GetMapping("/student")
    public String showStudentProfile() {
        return "student_profile";
    }
}
