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

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/clases")
public class ClasesController {

    private final ClasesService clasesService;
    private final UserService userService;
    private final AsignaturasService asignaturasService;

    @GetMapping("/viewAll")
    public String showAllClases(Model model, Principal principal) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> clases = clasesService.getOfertasByGrado(sessionUser.getGrado());
        if(!clases.isEmpty()) {
            model.addAttribute("clases", clases);
        } else {
            model.addAttribute("msg",true);
        }
        return "clases";
    }

    @GetMapping("/misAnuncios")
    public String showMisAnuncios(Model model, Principal principal) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> ofertas = clasesService.getOfertasByProfesor(sessionUser);
        if(!ofertas.isEmpty()) {
            model.addAttribute("ofertas", ofertas);
        } else {
            model.addAttribute("msg",true);
        }
        return "ofertas";
    }

    @GetMapping("/misSolicitudes")
    public String showMisSolicitudes(Model model, Principal principal) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Solicitud> solicitudes = clasesService.getSolicitudesByAlumno(sessionUser);
        if(!solicitudes.isEmpty()) {
            model.addAttribute("solicitudes", solicitudes);
        } else {
            model.addAttribute("msg",true);
        }
        return "solicitudes";
    }

    // **************** OFERTAS ****************

    @PostMapping("/nuevoAnuncio")
    public String showOfertaForm(Model model, Principal principal) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        model.addAttribute("oferta", new Oferta());
        model.addAttribute("asignaturas", asignaturasService.getAsignaturasByGrado(sessionUser.getGrado()));
        return "form_oferta";
    }

    @PostMapping("/createOferta")
    public String createOferta(@ModelAttribute("oferta") Oferta oferta, Principal principal) {
        oferta.setProfesor(userService.getUserByEmail(principal.getName()));
        clasesService.saveOferta(oferta);
        return "redirect:/clases/misAnuncios";
    }

    @GetMapping("/eliminarOferta/{id}")
    public String deleteOferta(@PathVariable("id") int id) {
        clasesService.deleteOfertaById(id);
        return "redirect:/clases/misAnuncios";
    }

    // **************** SOLICITUDES ****************

    @GetMapping("/solicitarClase/{id}")
    public String solicitarClase(@PathVariable("id") int id, Principal principal) {
        clasesService.createSolicitud(id, userService.getUserByEmail(principal.getName()));
        return "redirect:/clases/viewAll";
    }

    @GetMapping("/aceptarSolicitud/{id}")
    public String aceptarSolicitud(@PathVariable("id") int id, Principal principal) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        clasesService.aceptarSolicitud(id, sessionUser.getNombre(), sessionUser.getApellido());
        return "redirect:/clases/misAnuncios";
    }

    @GetMapping("/eliminarSolicitud/{id}")
    public String deleteSolicitud(@PathVariable("id") int id) {
        clasesService.deleteSolicitudById(id);
        return "redirect:/clases/misSolicitudes";
    }
}
