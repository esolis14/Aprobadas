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
@RequestMapping("/clases")
@SessionAttributes("vistaProf")
public class ClasesController {

    private final ClasesService clasesService;
    private final UserService userService;
    private final AsignaturasService asignaturasService;

    // **************** VISUALIZAR ****************

    // Todos los anuncios disponibles
    @GetMapping("/viewAll")
    public String showAllClases(Model model, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Oferta> clases = clasesService.getOfertasByGrado(sessionUser.getGrado());
        model.addAttribute("asignaturas", asignaturasService.getAllAsignaturas());
        // TODO: Repasar
        if(!clases.isEmpty()) {
            model.addAttribute("clases", clases);
        } else {
            model.addAttribute("msg",true);
        }
        model.addAttribute("vistaProf", vistaProf);
        return "clases";
    }

    // Anuncios publicados por el usuario
    @GetMapping("/misAnuncios")
    public String showMisAnuncios(Model model, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        model.addAttribute("nuevaOferta", new Oferta());
        model.addAttribute("asignaturas", asignaturasService.getAsignaturasByGrado(sessionUser.getGrado()));
        List<Oferta> ofertas = clasesService.getOfertasByProfesor(sessionUser);
        if(!ofertas.isEmpty()) {
            model.addAttribute("ofertas", ofertas);
        }
        model.addAttribute("vistaProf", vistaProf);
        return "anuncios";
    }

    // Clases solicitadas por el usuario
    @GetMapping("/misSolicitudes")
    public String showMisSolicitudes(Model model, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Solicitud> solicitudes = clasesService.getSolicitudesByAlumno(sessionUser);
        if(!solicitudes.isEmpty()) {
            model.addAttribute("solicitudes", solicitudes);
        } else {
            model.addAttribute("msg",true);
        }
        model.addAttribute("vistaProf", vistaProf);
        return "solicitudes";
    }

    // Clases ofertadas por el usuario y solicitadas por un alumno
    @GetMapping("/misOfertasSolicitadas")
    public String showMisOfertasSolicitadas(Model model, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        List<Solicitud> ofertasSolicitadas = clasesService.getSolicitudesByProfesor(sessionUser);
        if(!ofertasSolicitadas.isEmpty()) {
            model.addAttribute("solicitudes", ofertasSolicitadas);
        } else {
            model.addAttribute("msg",true);
        }
        model.addAttribute("vistaProf", vistaProf);
        return "ofertasSolicitadas";
    }

    // **************** OFERTAS ****************
    @PostMapping("/createAnuncio")
    public String createOferta(@ModelAttribute("oferta") Oferta oferta, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf, RedirectAttributes attributes) {
        oferta.setProfesor(userService.getUserByEmail(principal.getName()));
        clasesService.saveOferta(oferta);
        attributes.addFlashAttribute("vistaProf", vistaProf);
        return "redirect:/clases/misAnuncios";
    }

    @GetMapping("/eliminarAnuncio/{id}")
    public String deleteOferta(@PathVariable("id") int id, @ModelAttribute("vistaProf") boolean vistaProf, RedirectAttributes attributes) {
        clasesService.deleteOfertaById(id);
        attributes.addFlashAttribute("vistaProf", vistaProf);
        return "redirect:/clases/misAnuncios";
    }

    // **************** SOLICITUDES ****************
    @GetMapping("/solicitarClase/{id}")
    public String solicitarClase(@PathVariable("id") int id, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf, RedirectAttributes attributes) {
        clasesService.createSolicitud(id, userService.getUserByEmail(principal.getName()));
        attributes.addFlashAttribute("vistaProf", vistaProf);
        return "redirect:/clases/viewAll";
    }

    @GetMapping("/aceptarSolicitud/{id}")
    public String aceptarSolicitud(@PathVariable("id") int id, Principal principal, @ModelAttribute("vistaProf") boolean vistaProf, RedirectAttributes attributes) {
        User sessionUser = userService.getUserByEmail(principal.getName());
        clasesService.aceptarSolicitud(id, sessionUser.getNombre(), sessionUser.getApellido());
        attributes.addFlashAttribute("vistaProf", vistaProf);
        return "redirect:/clases/misOfertasSolicitadas";
    }

    @GetMapping("/eliminarSolicitud/{id}")
    public String deleteSolicitud(@PathVariable("id") int id, @ModelAttribute("vistaProf") boolean vistaProf, RedirectAttributes attributes) {
        clasesService.deleteSolicitudById(id);
        attributes.addFlashAttribute("vistaProf", vistaProf);
        return "redirect:/clases/misSolicitudes";
    }
}
