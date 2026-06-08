package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.DashboardResumenDTO;
import com.sanosysalvos.bff.service.DashboardBffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardBffControllerTest {

    @Mock
    private DashboardBffService service;

    @InjectMocks
    private DashboardBffController controller;

    @Test
    void verPantallaCompleta_deberiaRetornarDashboardCompleto() {
        DashboardResumenDTO dto = new DashboardResumenDTO();
        dto.setContadorAlertasPendientes(5);
        dto.setListaMascotas(new ArrayList<>());
        dto.setBuzonNotificaciones(new ArrayList<>());

        when(service.construirDashboardCompleto(1L, 1L)).thenReturn(dto);

        ResponseEntity<DashboardResumenDTO> response = controller.verPantallaCompleta(1L, 1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(5, response.getBody().getContadorAlertasPendientes());
        verify(service).construirDashboardCompleto(1L, 1L);
    }

    @Test
    void verPantallaCompleta_servicioCaido_deberiaLanzarExcepcion() {
        when(service.construirDashboardCompleto(anyLong(), anyLong()))
                .thenThrow(new RuntimeException("Service down"));

        assertThrows(RuntimeException.class, () -> controller.verPantallaCompleta(1L, 1L));
    }

    @Test
    void verPantallaCompleta_conParametrosDiferentes_deberiaLlamarConParametrosCorrectos() {
        DashboardResumenDTO dto = new DashboardResumenDTO();
        dto.setContadorAlertasPendientes(0);
        when(service.construirDashboardCompleto(100L, 200L)).thenReturn(dto);

        ResponseEntity<DashboardResumenDTO> response = controller.verPantallaCompleta(100L, 200L);

        assertEquals(200, response.getStatusCode().value());
        verify(service).construirDashboardCompleto(100L, 200L);
    }
}
