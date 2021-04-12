package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class FormController {

    private final UserService userService;

    @GetMapping({"/","/login"})
    public String showLogin(Model model) {
        model.addAttribute("usuario", new User());
        model.addAttribute("emailError", false);
        return "index";
    }

    @PostMapping("/enviarCodigo")
    public String enviarCodigo(@ModelAttribute User user, Model model) {
        if(userService.existeUsuario(user)) {
            model.addAttribute("emailError", true);
            return "/login";
        } else {
            userService.enviarCodigo(user);
            model.addAttribute("usuario", user);
            return "codigoForm";
        }
    }
}
