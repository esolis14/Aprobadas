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
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AsignaturasService asignaturasService;

    @GetMapping("/perfil")
    public String showProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "perfil";
    }

    @GetMapping("/editPerfil")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("grados", asignaturasService.getAllGrados());
        return "edit_profile";
    }

    @PostMapping("/updatePerfil")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/user/perfil";
    }
}
