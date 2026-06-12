package com.arkan.portalartikel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.assertj.core.api.Assertions.assertThat;

class PortalArtikelApplicationTest {

    @Test
    void applicationIsAnnotatedAsSpringBootApplication() {
        assertThat(PortalArtikelApplication.class.isAnnotationPresent(SpringBootApplication.class)).isTrue();
    }
}