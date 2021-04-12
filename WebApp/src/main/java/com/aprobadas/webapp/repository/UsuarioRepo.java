package com.aprobadas.webapp.repository;

import com.aprobadas.webapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    Usuario getUsuarioByEmail(String email);
    boolean existsUsuariosByEmail(String email);
}
