package com.sanosysalvos.bff.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AuthRequestDTOTest {

    @Test
    void probarGetterSetterYConstructor() {
        AuthRequestDTO dto = new AuthRequestDTO();
        assertNotNull(dto);

        // Forzamos el uso de métodos para que Jacoco marque las líneas como cubiertas
        dto.setUsername("usuario_test");
        dto.setPassword("password123");

        assertEquals("usuario_test", dto.getUsername());
        assertEquals("password123", dto.getPassword());
    }
}