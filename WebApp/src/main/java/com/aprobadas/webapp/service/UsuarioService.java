package com.aprobadas.webapp.service;

import com.aprobadas.webapp.model.Usuario;
import com.aprobadas.webapp.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;

    @Autowired // Más o menos, un constructor. No está claro si hace falta volver a poner @Autowired  o no
    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    // Comprueba si el usuario existe y la contraseña es correcta
    public boolean checkLogin(Usuario usuario) {
        return existeUsuario(usuario) && usuarioRepo.getUsuarioByEmail(usuario.getEmail()).getPassword().equals(usuario.getPassword());
    }

    public boolean existeUsuario(Usuario usuario) {
        return usuarioRepo.existsUsuariosByEmail(usuario.getEmail());
    }

    // Crea o actualiza un usuario
    public boolean crearUsuario(final Usuario nuevoUsuario) {
        if(existeUsuario(nuevoUsuario)) {
            nuevoUsuario.setId(usuarioRepo.getUsuarioByEmail(nuevoUsuario.getEmail()).getId());
            nuevoUsuario.setConfirmado(true);
            usuarioRepo.save(nuevoUsuario);
            return true;
        } else {
            usuarioRepo.save(nuevoUsuario);
            return false;
        }
    }

    public void enviarCodigo(final Usuario nuevoUsuario) {
        // Genera código aleatorio de 6 dígitos
        Random rnd = new Random();
        String codigo = String.format("%06d", rnd.nextInt(999999));
        // Envia email con código de verificación
        try {
            SendEmail email = new SendEmail();
            email.send(nuevoUsuario.getEmail(), codigo);
        } catch(RuntimeException re) {
            re.printStackTrace();
        }
        // Se guardan los datos del usuario en la Base de Datos
        crearUsuario(new Usuario(nuevoUsuario.getEmail(),codigo));
    }

    public boolean comprobarCodigo(final Usuario usuario, final String codigo) {
        Usuario usr = usuarioRepo.getUsuarioByEmail(usuario.getEmail());
        return usuario.getEmail().equals(usr.getEmail()) && usr.getCodigo().equals(codigo);
    }
}
