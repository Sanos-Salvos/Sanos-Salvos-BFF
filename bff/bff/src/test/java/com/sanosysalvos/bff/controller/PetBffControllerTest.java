package com.sanosysalvos.bff.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanosysalvos.bff.dto.PetBffDTO;
import com.sanosysalvos.bff.service.PetBffService;

@WebMvcTest(controllers = PetBffController.class)
@AutoConfigureMockMvc(addFilters = false)
class PetBffControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PetBffService petBffService;

    @Autowired
    private PetBffController petBffController;

    // Utilidad de Jackson para convertir objetos DTO a cadenas JSON en los POST/PUT
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(petBffController).build();
    }

    @Test
    void list_DeberiaRetornarListaDeMascotas() throws Exception {
        // Arrange
        List<PetBffDTO> mockList = Collections.singletonList(new PetBffDTO());
        when(petBffService.listarTodas()).thenReturn(mockList);

        // Act & Assert
        mockMvc.perform(get("/api/bff/pet/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void listByOrg_DeberiaRetornarListaPorOrganizacion() throws Exception {
        // Arrange
        List<PetBffDTO> mockList = Collections.singletonList(new PetBffDTO());
        // Apuntamos al método real: listarPorOrg
        when(petBffService.listarPorOrg(anyLong())).thenReturn(mockList);

        // Act & Assert
        mockMvc.perform(get("/api/bff/pet/organizacion/{orgId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById_DeberiaRetornarDetalleDeMascota() throws Exception {
        // Arrange
        PetBffDTO mockPet = new PetBffDTO();
        when(petBffService.obtenerDetalle(anyLong())).thenReturn(mockPet);

        // Act & Assert
        mockMvc.perform(get("/api/bff/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void create_DeberiaRegistrarMascota() throws Exception {
        // Arrange
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petBffService.registrarMascotaDesdeFront(any(PetBffDTO.class))).thenReturn(outputDto);

        // Act & Assert
        mockMvc.perform(post("/api/bff/pet/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk());
    }

    @Test
    void update_DeberiaActualizarMascota() throws Exception {
        // Arrange
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petBffService.actualizarMascota(anyLong(), any(PetBffDTO.class))).thenReturn(outputDto);

        // Act & Assert
        mockMvc.perform(put("/api/bff/pet/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk());
    }

    @Test
    void delete_DeberiaBorrarMascota() throws Exception {
        // Arrange
        // Al ser un método void en el servicio, usamos doNothing() de Mockito
        doNothing().when(petBffService).borrarMascota(anyLong());

        // Act & Assert
        mockMvc.perform(delete("/api/bff/pet/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}