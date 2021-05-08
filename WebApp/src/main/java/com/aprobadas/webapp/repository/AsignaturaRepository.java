package com.aprobadas.webapp.repository;

import com.aprobadas.webapp.model.Asignatura;
import com.aprobadas.webapp.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    Optional<Asignatura> findAsignaturaByNombre(String nombre);
    List<Asignatura> findAsignaturaByGrado(Grado grado);
}
