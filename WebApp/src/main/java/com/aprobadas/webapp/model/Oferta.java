package com.aprobadas.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "oferta")
@NoArgsConstructor
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private float precio;

    @ManyToOne(optional = false)
    private Asignatura asignatura;

    @ManyToOne(optional = false)
    private User profesor;

    public Oferta(String descripcion, Asignatura asignatura, User profesor) {
        this.descripcion = descripcion;
        this.asignatura = asignatura;
        this.profesor = profesor;
    }
}
