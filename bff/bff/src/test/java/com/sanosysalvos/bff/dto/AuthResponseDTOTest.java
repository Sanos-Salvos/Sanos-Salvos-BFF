package com.sanosysalvos.bff.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AuthResponseDTOTest {

    @Test
    void probarGetterSetterYConstructor() {
        AuthResponseDTO dto = new AuthResponseDTO();
        assertNotNull(dto);

        dto.setToken("jwt-token-de-prueba");

        assertEquals("jwt-token-de-prueba", dto.getToken());
    }
}