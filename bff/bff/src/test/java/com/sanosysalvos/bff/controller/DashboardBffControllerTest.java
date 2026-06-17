package com.sanosysalvos.bff.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sanosysalvos.bff.dto.DashboardResumenDTO;
import com.sanosysalvos.bff.service.DashboardBffService;

@WebMvcTest(controllers = DashboardBffController.class)
@AutoConfigureMockMvc(addFilters = false)
class DashboardBffControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private DashboardBffService dashboardBffService;

    @Autowired
    private DashboardBffController dashboardBffController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(dashboardBffController).build();
    }

    @Test
    void getResumen_DeberiaRetornarOk() throws Exception {
        // Arrange
        DashboardResumenDTO mockResponse = new DashboardResumenDTO();

        // Simula el método REAL que existe en tu controlador pasándole comodines numéricos (anyLong)
        when(dashboardBffService.construirDashboardCompleto(anyLong(), anyLong()))
                .thenReturn(mockResponse);

        // Act & Assert
        // Se cambió a la ruta real "/api/bff/main/dashboard" y se agregaron los @RequestParam obligatorios
        mockMvc.perform(get("/api/bff/main/dashboard")
                        .param("usuarioId", "1")
                        .param("organizacionId", "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}