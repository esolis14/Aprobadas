package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.service.AsignaturasService;
import com.aprobadas.webapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class AppController {

    private final UserService userService;
    private final AsignaturasService asignaturasService;

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/edit")
    public String showStudentProfile() {
        return "edit_profile";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "profile";
    }

    @PostMapping("/edit")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("grados", asignaturasService.getAllGrados());
        return "edit_profile";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/profile";
    }
}
