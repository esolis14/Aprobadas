package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Role;
import com.aprobadas.webapp.model.Usuario;
import com.aprobadas.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Buscar usuario y si no existe lanzar una excepción
        com.aprobadas.webapp.model.User appUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No existe un usuario con ese email."));

        // Mapear nuestra lista de Authority con la de spring security
        List grantList = new ArrayList();
        for (Role role: appUser.getRoles()) {
            //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            grantList.add(new SimpleGrantedAuthority(role.getRole()));
        }

        //Crear el objeto UserDetails que va a ir en sesion y retornarlo.
        return new User(appUser.getEmail(), appUser.getPassword(), grantList);
    }

    public boolean existeUsuario(com.aprobadas.webapp.model.User user) {
        return userRepository.existsUsersByEmail(user.getEmail());
    }

    // Crea o actualiza un usuario
    public boolean crearUsuario(final com.aprobadas.webapp.model.User newUser) {
        if(existeUsuario(newUser)) {
            newUser.setId(userRepository.findByEmail(newUser.getEmail()).get().getId());
            newUser.setConfirmed(true);
            userRepository.save(newUser);
            return true;
        } else {
            userRepository.save(newUser);
            return false;
        }
    }

    public void enviarCodigo(final com.aprobadas.webapp.model.User newUser) {
        // Genera código aleatorio de 6 dígitos
        Random rnd = new Random();
        String codigo = String.format("%06d", rnd.nextInt(999999));
        // Envia email con código de verificación
        try {
            SendEmail email = new SendEmail();
            email.send(newUser.getEmail(), codigo);
        } catch(RuntimeException re) {
            re.printStackTrace();
        }
        // Se guardan los datos del usuario en la Base de Datos
        crearUsuario(new com.aprobadas.webapp.model.User(newUser.getEmail(), codigo));
    }

}
