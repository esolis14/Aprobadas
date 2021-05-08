package com.aprobadas.webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "solicitud")
@NoArgsConstructor
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "valoracion")
    private int valoracion;

    @ManyToOne(optional = false)
    private Oferta oferta;

    @ManyToOne(optional = false)
    private User user;

    public Solicitud(boolean accepted, Oferta oferta, User user) {
        this.accepted = accepted;
        this.oferta = oferta;
        this.user = user;
    }
}
