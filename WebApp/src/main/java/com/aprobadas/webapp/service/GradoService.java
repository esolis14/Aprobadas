package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Grado;
import com.aprobadas.webapp.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradoService {

    @Autowired
    GradoRepository gradoRepository;

    public List<Grado> getAllGrados() {
        return gradoRepository.findAll();
    }
}
