package com.aprobadas.webapp.Oferta;

import com.aprobadas.webapp.model.Asignatura;
import com.aprobadas.webapp.model.Grado;
import com.aprobadas.webapp.model.Oferta;
import com.aprobadas.webapp.model.User;
import com.aprobadas.webapp.repository.OfertaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OfertaRepositoryTest {

        @Autowired private TestEntityManager testEntityManager;
        @Autowired private OfertaRepository ofertaRepository;

        @Test
        public void saveOferta() {
            User user = new User("prueba@gmail.com", "123456");
            Grado grado = new Grado("Grado en Ingeniería en Tecnología Industrial");
            Asignatura asignatura = new Asignatura("Análisis de Circuitos", 1, grado);
            Oferta oferta = new Oferta("Descripción", asignatura, user);
            testEntityManager.persistAndFlush(user);
            testEntityManager.persistAndFlush(grado);
            testEntityManager.persistAndFlush(asignatura);
            testEntityManager.persistAndFlush(oferta);
            assertThat(oferta.getId()).isNotNull();
        }

        @Test
        public void deleteOferta() {
            User user1 = new User("prueba@gmail.com", "123456");
            User user2 = new User("prueba2@gmail.com", "123456");
            Grado grado = new Grado("Grado en Ingeniería en Tecnología Industrial");
            Asignatura asignatura = new Asignatura("Análisis de Circuitos", 1, grado);
            testEntityManager.persistAndFlush(user1);
            testEntityManager.persistAndFlush(user2);
            testEntityManager.persistAndFlush(grado);
            testEntityManager.persistAndFlush(asignatura);
            testEntityManager.persistAndFlush(new Oferta("Descripción", asignatura, user1));
            testEntityManager.persistAndFlush(new Oferta("Descripción", asignatura, user2));
            ofertaRepository.deleteAll();
            assertThat(ofertaRepository.findAll()).isEmpty();
        }
    }
