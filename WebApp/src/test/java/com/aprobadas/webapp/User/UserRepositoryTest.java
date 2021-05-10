package com.aprobadas.webapp.User;

import com.aprobadas.webapp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveUser() {
        User user = new User("prueba@gmail.com", "123456");
        testEntityManager.persistAndFlush(user);
        assertThat(user.getId()).isNotNull();
    }
}
