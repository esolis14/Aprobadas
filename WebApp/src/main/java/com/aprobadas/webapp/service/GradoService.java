package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Grado;
import com.aprobadas.webapp.repository.GradoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradoService {

    private final GradoRepository gradoRepository;

    public List<Grado> getAllGrados() {
        return gradoRepository.findAll();
    }
}
