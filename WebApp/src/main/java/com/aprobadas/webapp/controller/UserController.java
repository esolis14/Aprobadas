package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.Solicitud;
import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.service.AsignaturasService;
import com.aprobadas.webapp.service.ClasesService;
import com.aprobadas.webapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
@SessionAttributes("vistaProf")
public class UserController {

    private final UserService userService;
    private final AsignaturasService asignaturasService;
    private final ClasesService clasesService;

    @GetMapping("/perfil")
    public String showPerfile(Model model, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> listaOfertas = clasesService.getOfertasByProfesor(sessionUser);
        List<Solicitud> listaSolicitudesAceptadas = clasesService.getSolicitudesByProfesor(sessionUser);
        model.addAttribute("user", sessionUser);
        model.addAttribute("numAnuncios", listaOfertas.size());
        model.addAttribute("numSolicitudes", listaSolicitudesAceptadas.size());
        model.addAttribute("grados", asignaturasService.getAllGrados());
        model.addAttribute("edit", true);
        model.addAttribute("vistaProf", vistaProf);
        return "perfil";
    }

    @GetMapping("/perfilUser/{id}")
    public String showPerfilProfesor(@PathVariable("id") int id, Model model, @ModelAttribute("vistaProf") boolean vistaProf) {
        User user = userService.getUserById(id);
        List<Oferta> listaOfertas = clasesService.getOfertasByProfesor(user);
        List<Solicitud> listaSolicitudesAceptadas = clasesService.getSolicitudesByProfesor(user);
        model.addAttribute("user", user);
        model.addAttribute("numAnuncios", listaOfertas.size());
        model.addAttribute("numSolicitudes", listaSolicitudesAceptadas.size());
        model.addAttribute("edit", false);
        model.addAttribute("vistaProf", vistaProf);
        return "perfil";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, @ModelAttribute("vistaProf") boolean vistaProf, RedirectAttributes attributes) {
        userService.updateUser(user);
        attributes.addFlashAttribute("vistaProf", vistaProf);
        return "redirect:/user/perfil";
    }
}
