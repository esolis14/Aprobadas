package com.aprobadas.webapp.controller;

import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.service.ClasesService;
import com.aprobadas.webapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/clases")
public class ClasesController {

    private final ClasesService clasesService;
    private final UserService userService;

    @GetMapping("/viewAll")
    public String showAllClases(Model model, Principal principal) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> ofertas = clasesService.getOfertasByGrado(sessionUser.getGrado());
        if(!ofertas.isEmpty()) {
            model.addAttribute("clases",ofertas);
        } else {
            model.addAttribute("msg",true);
        }
        return "clases";
    }

    @GetMapping("/misOfertas")
    public String showMisOfertas(Model model, Principal principal) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> ofertas = clasesService.getOfertasByProfesor(sessionUser);
        if(!ofertas.isEmpty()) {
            model.addAttribute("ofertas",ofertas);
        } else {
            model.addAttribute("msg",true);
        }
        return "ofertas";
    }

    /*@GetMapping("/nuevaOferta")
    public String createOferta(Model model) {
        model.addAttribute("oferta", new Oferta());
        return "create_oferta";
    }*/

    @GetMapping("/eliminarOferta/{id}")
    public String deleteOferta(@PathVariable("id") int id, Model model, Principal principal) {
        clasesService.deleteOfertaById(id);
        return "redirect:/clases/misOfertas";
    }
}
