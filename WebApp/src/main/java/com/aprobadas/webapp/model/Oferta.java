package com.aprobadas.webapp.model;

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

    @Column(name = "valoracion")
    private int valoracion;

    @ManyToOne(optional = false)
    private Asignatura asignatura;

    @ManyToOne(optional = false)
    private User profesor;
}
