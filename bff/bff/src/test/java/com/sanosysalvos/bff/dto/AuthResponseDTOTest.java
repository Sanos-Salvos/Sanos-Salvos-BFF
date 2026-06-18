package com.sanosysalvos.bff.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AuthResponseDTOTest {

    @Test
    void probarGetterSetterYConstructor() {
        AuthResponseDTO dto = new AuthResponseDTO();
        assertNotNull(dto);

        dto.setAutenticado(true);
        dto.setUsername("usuarioPrueba");
        dto.setRol("ROLE_USER");

        assertTrue(dto.isAutenticado());
        assertEquals("usuarioPrueba", dto.getUsername());
        assertEquals("ROLE_USER", dto.getRol());
    }
}