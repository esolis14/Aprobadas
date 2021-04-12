package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.Usuario;
import com.aprobadas.webapp.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
/*
@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/")
    public String showLoginForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("loginError", false); // Ocultar mensaje de error
        return "home";
    }
/*
    @PostMapping("/login")
    public String loginUsuario(@ModelAttribute Usuario usuario, Model model) {
        if(usuarioService.checkLogin(usuario)) {
            //model.addAttribute("usuario", usuario);
            return "redirect:/home";
        } else {
            model.addAttribute("usuario", usuario);
            model.addAttribute("loginError", true); // Mostrar mensaje de error
            return "index";
        }
    }

    @GetMapping("/emailForm")
    public String showEmailForm(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("emailError", false);
        return "emailForm";
    }

    @PostMapping("/enviarCodigo")
    public String enviarCodigo(@ModelAttribute Usuario usuario, Model model) {
        if(usuarioService.existeUsuario(usuario)) {
            model.addAttribute("emailError", true);
            return "/emailForm";
        } else {
            usuarioService.enviarCodigo(usuario);
            model.addAttribute("usuario", usuario);
            return "codigoForm";
        }
    }

    @PostMapping("/comprobarCodigo")
    public String validarCodigo(@ModelAttribute Usuario usuario, Model model) {
        if(usuarioService.comprobarCodigo(usuario, usuario.getCodigo())) {
            // Código correcto
            model.addAttribute("usuario", usuario);
            return "registerForm";
        } else {
            // Código incorrecto
            return "redirect:/loginForm";
        }
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        if(usuarioService.crearUsuario(usuario)) {
            return "home";
        } else {
            return "redirect:/access-denied";
        }
    }
}
*/