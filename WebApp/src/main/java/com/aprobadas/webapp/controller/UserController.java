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
    public String showPerfil(Model model, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> listaOfertas = clasesService.getOfertasByProfesor(sessionUser);
        List<Solicitud> listaSolicitudesAceptadas = clasesService.getSolicitudesByProfesor(sessionUser);
        double mediaVal = listaSolicitudesAceptadas.stream().map(Solicitud::getValoracion).mapToInt(a -> a).average().orElse(0);
        model.addAttribute("user", sessionUser);
        model.addAttribute("numAnuncios", listaOfertas.size());
        model.addAttribute("numSolicitudes", listaSolicitudesAceptadas.size());
        model.addAttribute("edit", true);
        model.addAttribute("vistaProf", vistaProf);
        model.addAttribute("satisfaccion", (int)(100*(mediaVal/5)));
        return "perfil";
    }

    @GetMapping("/editPerfil")
    public String showEditPerfil(Model model, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> listaOfertas = clasesService.getOfertasByProfesor(sessionUser);
        List<Solicitud> listaSolicitudesAceptadas = clasesService.getSolicitudesByProfesor(sessionUser);
        double mediaVal = listaSolicitudesAceptadas.stream().map(Solicitud::getValoracion).mapToInt(a -> a).average().orElse(0);
        model.addAttribute("user", sessionUser);
        model.addAttribute("numAnuncios", listaOfertas.size());
        model.addAttribute("numSolicitudes", listaSolicitudesAceptadas.size());
        model.addAttribute("grados", asignaturasService.getAllGrados());
        model.addAttribute("vistaProf", vistaProf);
        model.addAttribute("emailError", false);
        model.addAttribute("satisfaccion", (int)(100*(mediaVal/5)));
        return "edit_perfil";
    }

    @GetMapping("/perfilUser/{id}")
    public String showPerfilProfesor(@PathVariable("id") int id, Model model, @ModelAttribute("vistaProf") boolean vistaProf) {
        User user = userService.getUserById(id);
        List<Oferta> listaOfertas = clasesService.getOfertasByProfesor(user);
        List<Solicitud> listaSolicitudesAceptadas = clasesService.getSolicitudesByProfesor(user);
        double mediaVal = listaSolicitudesAceptadas.stream().map(Solicitud::getValoracion).mapToInt(a -> a).average().orElse(0);
        model.addAttribute("user", user);
        model.addAttribute("numAnuncios", listaOfertas.size());
        model.addAttribute("numSolicitudes", listaSolicitudesAceptadas.size());
        model.addAttribute("edit", false);
        model.addAttribute("vistaProf", vistaProf);
        model.addAttribute("satisfaccion", (int)(100*(mediaVal/5)));
        return "perfil";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, @ModelAttribute("vistaProf") boolean vistaProf, Model model, Principal principal, RedirectAttributes attributes) {
        if(userService.existsUserByEmail(user) && !user.getEmail().equals(principal.getName())) {
            model.addAttribute("existsEmail", true);
            attributes.addFlashAttribute("vistaProf", vistaProf);
            User sessionUser = userService.getUserByEmail(principal.getName());
            List<Oferta> listaOfertas = clasesService.getOfertasByProfesor(sessionUser);
            List<Solicitud> listaSolicitudesAceptadas = clasesService.getSolicitudesByProfesor(sessionUser);
            double mediaVal = listaSolicitudesAceptadas.stream().map(Solicitud::getValoracion).mapToInt(a -> a).average().orElse(0);
            model.addAttribute("user", sessionUser);
            model.addAttribute("numAnuncios", listaOfertas.size());
            model.addAttribute("numSolicitudes", listaSolicitudesAceptadas.size());
            model.addAttribute("grados", asignaturasService.getAllGrados());
            model.addAttribute("vistaProf", vistaProf);
            model.addAttribute("emailError", true);
            model.addAttribute("satisfaccion", (int)(100*(mediaVal/5)));
            return "edit_perfil";
        } else {
            userService.updateUser(user);
            attributes.addFlashAttribute("vistaProf", vistaProf);
            return "redirect:/user/perfil";
        }
    }
}
