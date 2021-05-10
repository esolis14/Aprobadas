package com.aprobadas.webapp.repository;

import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.Solicitud;
import com.aprobadas.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findSolicitudsByUser(User user);
    List<Solicitud> findSolicitudByOferta(Oferta oferta);
    List<Solicitud> findSolicitudByOferta_Profesor(User user);
}
