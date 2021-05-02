package com.aprobadas.webapp.config;

import com.aprobadas.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserService userService;

    @Bean // Encriptador de contraseñas
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**","/img/**","/js/**").permitAll()
                .antMatchers("/", "/home", "/login", "/form*").permitAll()
                .antMatchers("/admin*").access("hasRole('ADMIN')")
                .antMatchers("/user*", "/clases*").access("hasRole('USER') or hasRole('ADMIN')")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()// Personaliza el proceso de inicio de sesión
                    .loginPage("/login") // URL de la página de inicio de sesión
                    .permitAll()
                    .defaultSuccessUrl("/home") // URL a la que será redigirido trás el inicio de sesión
                    .failureUrl("/login?error=true") // URL a la que será redigirido cuando el inicio de sesión falla
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .and()
                .logout() // Personaliza el proceso de cierre de sesión
                    .permitAll()
                    .logoutSuccessUrl("/login?logout");
    }

    // Registra el service para usuarios y el encriptador de contraseña
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Setting Service to find User in the database and Setting PassswordEncoder
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
