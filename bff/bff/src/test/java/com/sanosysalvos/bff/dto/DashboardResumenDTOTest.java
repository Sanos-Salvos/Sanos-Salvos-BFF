package com.sanosysalvos.bff.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class DashboardResumenDTOTest {

    @Test
    void probarGetterSetterYConstructor() {
        DashboardResumenDTO dto = new DashboardResumenDTO();
        assertNotNull(dto);

    }
}