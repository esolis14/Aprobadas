package com.aprobadas.webapp.repository;

import com.aprobadas.webapp.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Integer> {
    Optional<Grado> findGradoByNombre(String nombre);
}
