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
public class ClasesService {

    private final OfertaRepository ofertaRepository;
    private final SolicitudRepository solicitudRepository;
    private final SendEmail sendEmail;

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
            System.out.print(ex.getMessage());
        }
    }

    public void actualizarValoracionOferta(int ofertaId){
        List<Solicitud> solicitudes = solicitudRepository.findSolicitudByOferta(ofertaRepository.getOne(ofertaId));
        int rate = 0;
        for (Solicitud solicitud: solicitudes) {
            rate += solicitud.getValoracion();
        }
        rate = rate/solicitudes.size();
        Oferta oferta = ofertaRepository.getOne(ofertaId);
        oferta.setValoracion(rate);
        ofertaRepository.save(oferta);
    }

    // ************ Funciones SOLICITUDES ************

    public List<Solicitud> getSolicitudesByAlumno(User user) { return solicitudRepository.findSolicitudsByUser(user); }
    public List<Solicitud> getSolicitudesByProfesor(User user) { return solicitudRepository.findSolicitudByOferta_Profesor(user); }
    public Solicitud getSolicitudById(int id) { return solicitudRepository.getOne(id); }

    public void saveSolicitud(Solicitud solicitud) {
        solicitudRepository.save(solicitud);
    }

    public void createSolicitud(int ofertaId, User user) {
        Oferta oferta = ofertaRepository.getOne(ofertaId);

        solicitudRepository.save(new Solicitud(false, oferta, user));

        // Se envía notificación de solicitud al profesor
        try {
            sendEmail.sendNotificacionSolicitud(oferta.getProfesor().getEmail(), user.getNombre(), user.getApellido());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void deleteSolicitudById(int id) { solicitudRepository.deleteById(id); }

    public void aceptarSolicitud(int id, String nombre, String apellido) {
        Solicitud solicitud = solicitudRepository.getOne(id);
        solicitud.setAccepted(true);
        solicitudRepository.save(solicitud);

        // Se envía notificación de aceptación al alumno
        try {
            sendEmail.sendNotificacionAceptacion(solicitud.getUser().getEmail(), nombre, apellido);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
}
