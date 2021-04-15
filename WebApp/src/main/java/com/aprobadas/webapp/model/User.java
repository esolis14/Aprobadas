package com.aprobadas.webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String tlf;
    private String code;
    private boolean enabled; // Comprobar si interfiere en temas de seguridad.

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
