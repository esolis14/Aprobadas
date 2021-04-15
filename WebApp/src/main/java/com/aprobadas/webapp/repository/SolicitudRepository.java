package com.aprobadas.webapp.repository;

import com.aprobadas.webapp.model.Solicitud;
import com.aprobadas.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findSolicitudByUser(User user);
}
