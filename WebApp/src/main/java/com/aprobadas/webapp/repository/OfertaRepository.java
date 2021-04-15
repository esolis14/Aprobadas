package com.aprobadas.webapp.repository;

import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
    List<Oferta> findOfertaByProfesor(User profesor);
}
