package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class AppController {

    @GetMapping({"/","/login"})
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("emailError", false);
        return "index";
    }

    @GetMapping("/home")
    public String showHome(Principal principal, Model model, HttpSession session) {
        if(session.getAttribute("vista") == null) {
            session.setAttribute("vista", "Profesor");
        }
        model.addAttribute("email", principal.getName());
        return "home";
    }

    @PostMapping("/changeView")
    public String changeView(Principal principal, Model model, HttpSession session) {
        if(session.getAttribute("vista").equals("Profesor")) {
            session.setAttribute("vista", "Alumno");
        } else {
            session.setAttribute("vista", "Profesor");
        }
        model.addAttribute("email", principal.getName());
        return "home";
    }
}
