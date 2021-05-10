package com.aprobadas.webapp;

import com.aprobadas.webapp.controller.AppController;
import com.aprobadas.webapp.controller.ClasesController;
import com.aprobadas.webapp.controller.FormController;
import com.aprobadas.webapp.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AprobadasWebAppApplicationTests {

    @Autowired ClasesController clasesController;
    @Autowired UserController userController;
    @Autowired FormController formController;

    @Test
    void contextLoads() {
        assertThat(clasesController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(formController).isNotNull();
    }

}
