package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/form")
public class FormController {

    @Autowired
    private final UserService userService;

    @PostMapping("/sendCode")
    public String sendVerificationCode(@ModelAttribute("user") User user, Model model) {
        if(userService.existsUserByEmail(user)) {
            model.addAttribute("emailError", true);
            return "index";
        } else {
            userService.sendCode(user);
            model.addAttribute("user", user);
            model.addAttribute("codeError", false);
            return "/code";
        }
    }

    @PostMapping("/checkCode")
    public String checkCode(@ModelAttribute("user") User user, Model model) {
        if(userService.checkCode(user)) {
            model.addAttribute("user", user);
            return "registration";
        } else {
            user.setCode(null);
            model.addAttribute("user", user);
            model.addAttribute("codeError", true);
            return "code";
        }
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        userService.saveUser(user);
        return "redirect:/login";
    }
}
