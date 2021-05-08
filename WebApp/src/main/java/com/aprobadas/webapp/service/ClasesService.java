package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Grado;
import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.Solicitud;
import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.repository.OfertaRepository;
import com.aprobadas.webapp.repository.SolicitudRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ClasesService {

    OfertaRepository ofertaRepository;
    SolicitudRepository solicitudRepository;
    SendEmail sendEmail;

    @Autowired
    public ClasesService(OfertaRepository ofertaRepository, SolicitudRepository solicitudRepository, SendEmail sendEmail) {
        this.ofertaRepository = ofertaRepository;
        this.solicitudRepository = solicitudRepository;
        this.sendEmail = sendEmail;
    }


    // ************ Funciones OFERTAS ************

    public List<Oferta> getOfertasByGrado(Grado grado) {
        List<Oferta> allOfertas = ofertaRepository.findAll();
        allOfertas.removeIf(oferta -> !oferta.getAsignatura().getGrado().equals(grado));
        return allOfertas;
    }

    public List<Oferta> getOfertasByProfesor(User profesor) {
        return ofertaRepository.findOfertasByProfesor(profesor);
    }

    public void saveOferta(Oferta oferta) {
        ofertaRepository.save(oferta);
    }

    public void deleteOfertaById(int ofertaId) {
        // Se borran las solicitudes asociadas a la oferta
        List<Solicitud> solicitudes = solicitudRepository.findSolicitudByOferta(ofertaRepository.getOne(ofertaId));
        for (Solicitud solicitud: solicitudes) deleteSolicitudById(solicitud.getId());

        // Se borra la oferta
        try {
            ofertaRepository.deleteById(ofertaId);
        } catch(Exception ex) {
            System.out.printf(ex.getMessage());
        }
    }


    // ************ Funciones SOLICITUDES ************

    public List<Solicitud> getSolicitudesByAlumno(User user) { return solicitudRepository.findSolicitudsByUser(user); }
    public List<Solicitud> getSolicitudesByProfesor(User user) { return solicitudRepository.findSolicitudByOferta_Profesor(user); }

    public void createSolicitud(int ofertaId, User user) {
        Oferta oferta = ofertaRepository.getOne(ofertaId);

        // TODO: Buscar si el usuario ya ha enviado una solicitud.
        solicitudRepository.save(new Solicitud(false, oferta, user));

        // Se envía notificación de solicitud al profesor
        sendEmail.sendNotificacionSolicitud(oferta.getProfesor().getEmail(), user.getNombre(), user.getApellido());
    }

    public void deleteSolicitudById(int id) {
        solicitudRepository.deleteById(id);
    }

    public void aceptarSolicitud(int id, String nombre, String apellido) {
        Solicitud solicitud = solicitudRepository.getOne(id);
        solicitud.setAccepted(true);
        solicitudRepository.save(solicitud);

        // Se envía notificación de aceptación al alumno
        sendEmail.sendNotificacionAceptacion(solicitud.getUser().getEmail(), nombre, apellido);
    }
}
