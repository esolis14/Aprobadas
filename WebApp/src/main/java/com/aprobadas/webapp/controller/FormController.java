package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.service.AsignaturasService;
import com.aprobadas.webapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/form")
public class FormController {

    private final UserService userService;
    private final AsignaturasService asignaturasService;

    @PostMapping("/sendCode")
    public String sendVerificationCode(@ModelAttribute("user") User user, Model model) {
        if(userService.existsUserByEmail(user)) {
            model.addAttribute("emailError", true);
            return "/login";
        } else {
            userService.sendCode(user);
            model.addAttribute("user", user);
            model.addAttribute("codeError", false);
            return "verification_code";
        }
    }

    @PostMapping("/checkCode")
    public String checkCode(@ModelAttribute("user") User user, Model model) {
        if(userService.checkCode(user)) {
            model.addAttribute("user", user);
            model.addAttribute("grados", asignaturasService.getAllGrados());
            return "registration";
        } else {
            user.setCode(null);
            model.addAttribute("user", user);
            model.addAttribute("codeError", true);
            return "verification_code";
        }
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }
}
