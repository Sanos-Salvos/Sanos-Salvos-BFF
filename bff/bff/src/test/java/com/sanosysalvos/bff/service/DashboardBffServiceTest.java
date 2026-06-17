package com.sanosysalvos.bff.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sanosysalvos.bff.dto.DashboardResumenDTO;


@ExtendWith(MockitoExtension.class)
class DashboardBffServiceTest {

    @InjectMocks
    private DashboardBffService dashboardBffService;

    @Test
    void construirDashboardCompleto_DeberiaRetornarDashboardResumenDTO() {

        Long usuarioId = 1L;
        Long organizacionId = 1L;
        DashboardResumenDTO expectedResponse = new DashboardResumenDTO();


        DashboardResumenDTO actualResponse = dashboardBffService.construirDashboardCompleto(usuarioId, organizacionId);

        assertNotNull(actualResponse, "El objeto DashboardResumenDTO no debería ser nulo");
    }
}