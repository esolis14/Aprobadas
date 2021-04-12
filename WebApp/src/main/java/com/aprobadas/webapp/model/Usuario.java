package com.aprobadas.webapp.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;
    private String tlf;
    private String codigo;
    private boolean confirmado;

    public Usuario(String email, String codigo) {
        this.email = email;
        this.password = null;
        this.nombre = null;
        this.apellido = null;
        this.tlf = null;
        this.codigo = codigo;
        this.confirmado = false;
    }
}

