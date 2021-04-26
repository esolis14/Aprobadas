package com.aprobadas.webapp.repository;

import com.aprobadas.webapp.model.Grado;
import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
    List<Oferta> findOfertaByProfesor(User profesor);
}
