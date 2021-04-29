package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Asignatura;
import com.aprobadas.webapp.model.Grado;
import com.aprobadas.webapp.repository.AsignaturaRepository;
import com.aprobadas.webapp.repository.GradoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AsignaturasService {

    private final GradoRepository gradoRepository;
    private final AsignaturaRepository asignaturaRepository;

    public List<Grado> getAllGrados() {
        return gradoRepository.findAll();
    }
    public List<Asignatura> getAllAsignaturas() { return asignaturaRepository.findAll(); }

    public List<Asignatura> getAsignaturasByGrado(Grado grado) {
        return asignaturaRepository.findAsignaturaByGrado(grado);
    }
}
