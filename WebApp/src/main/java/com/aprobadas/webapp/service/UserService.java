package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Role;
import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.repository.RoleRepository;
import com.aprobadas.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User appUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No existe un usuario con ese email."));
        /*if (appUser == null) { throw new UsernameNotFoundException("No existe un usuario con ese email.");}*/

        // Se mapea la lista de roles con la de spring security
        List grantList = new ArrayList();
        for (Role role: appUser.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            grantList.add(grantedAuthority);
        }

        // Se crea el objeto UserDetails que va a ir en la sesión
        return new org.springframework.security.core.userdetails.User(appUser.getEmail(), appUser.getPassword(), grantList);
    }

    public boolean existsUserByEmail(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

    public void saveUser(final User newUser) {
        if(existsUserByEmail(newUser)) {
            newUser.setId(userRepository.findByEmail(newUser.getEmail()).get().getId());
            newUser.setEnabled(true);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); // Se encripta la contraseña
            // Se asigna el role USER al nuevo usuario
            if(roleRepository.findByRole("ROLE_USER").isPresent()) {
                newUser.setRoles(Collections.singletonList(roleRepository.findByRole("ROLE_USER").get()));
            } else {
                newUser.setRoles(Collections.singletonList(new Role("ROLE_USER")));
            }
        }
        userRepository.save(newUser);
    }

    public void sendCode(final User newUser) {
        // Se genera código aleatorio de 6 dígitos
        String code = String.format("%06d", new Random().nextInt(999999));
        // Se envía email con código de verificación
        try {
            SendEmail email = new SendEmail();
            email.send(newUser.getEmail(), code);
        } catch(RuntimeException re) {
            re.printStackTrace();
        }
        // Se guardan los datos del usuario en la Base de Datos
        saveUser(new User(newUser.getEmail(), code));
    }

    public boolean checkCode(final User registration) {
        if(userRepository.findByEmail(registration.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(registration.getEmail()).get();
            return user.getEmail().equals(registration.getEmail()) && user.getCode().equals(registration.getCode());
        } else {
            throw new UsernameNotFoundException("No existe un usuario con ese email.");
        }
    }
}
