package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Grado;
import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.repository.OfertaRepository;
import com.aprobadas.webapp.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasesService {

    @Autowired
    OfertaRepository ofertaRepository;

    @Autowired
    SolicitudRepository solicitudRepository;

    public ClasesService(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    public List<Oferta> getOfertasByGrado(Grado grado) {
        List<Oferta> allOfertas = ofertaRepository.findAll();
        allOfertas.removeIf(oferta -> !oferta.getAsignatura().getGrado().equals(grado));
        return allOfertas;
    }

    public List<Oferta> getOfertasByProfesor(User profesor) {
        return ofertaRepository.findOfertaByProfesor(profesor);
    }

    public void saveOferta(Oferta oferta) {
        ofertaRepository.save(oferta);
    }

    public void deleteOfertaById(int ofertaId) {
        ofertaRepository.deleteById(ofertaId);
    }

}
