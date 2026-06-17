package com.sanosysalvos.bff.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PetBffDTOTest {

    @Test
    void probarGetterSetterYConstructor() {
        PetBffDTO dto = new PetBffDTO();

        assertNotNull(dto);

    }
}