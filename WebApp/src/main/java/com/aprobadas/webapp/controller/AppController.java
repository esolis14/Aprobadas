package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes("vistaProf")
public class AppController {

    @ModelAttribute("vistaProf")
    public boolean vista() { return true; }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/home")
    public String showHome(Model model, @ModelAttribute("vistaProf") boolean vistaProf) {
        model.addAttribute("vistaProf", vistaProf);
        return "home";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("emailError", false);
        return "login";
    }

    @GetMapping("/cambiarVista")
    public String cambiarVista(Model model, @ModelAttribute("vistaProf") boolean vistaProf){
        if(vistaProf == true) {
            model.addAttribute("vistaProf", false);
        } else {
            model.addAttribute("vistaProf", true);
        }
        return "home";
    }
}
