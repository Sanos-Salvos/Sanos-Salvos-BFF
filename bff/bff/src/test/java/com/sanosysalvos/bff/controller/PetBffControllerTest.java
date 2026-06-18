package com.sanosysalvos.bff.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(petBffController).build();
    }

    @Test
    void listar_DeberiaRetornarListaDeMascotas() throws Exception {
        // Arrange - Se cambia listarTodas() por listar()
        List<PetBffDTO> mockList = Collections.singletonList(new PetBffDTO());
        when(petBffService.listar()).thenReturn(mockList);

        // Act & Assert - Se corrige la URL de /pet/list a la raíz /pet
        mockMvc.perform(get("/api/bff/pet")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerPorId_DeberiaRetornarDetalleDeMascota() throws Exception {
        // Arrange - Se cambia obtenerDetalle() por obtenerPorId()
        PetBffDTO mockPet = new PetBffDTO();
        when(petBffService.obtenerPorId(anyLong())).thenReturn(mockPet);

        // Act & Assert
        mockMvc.perform(get("/api/bff/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void registrar_DeberiaRegistrarMascota() throws Exception {
        // Arrange - Se cambia registrarMascotaDesdeFront() por registrar()
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petBffService.registrar(any(PetBffDTO.class))).thenReturn(outputDto);

        // Act & Assert - Se corrige la URL de /pet/create al POST de la raíz /pet
        mockMvc.perform(post("/api/bff/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizar_DeberiaActualizarMascota() throws Exception {
        // Arrange - Se cambia actualizarMascota() por actualizar()
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petBffService.actualizar(anyLong(), any(PetBffDTO.class))).thenReturn(outputDto);

        // Act & Assert - Se corrige la URL de /pet/update/{id} a /{id}
        mockMvc.perform(put("/api/bff/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk());
    }

    @Test
    void eliminar_DeberiaBorrarMascota() throws Exception {
        // Arrange - Se cambia borrarMascota() por eliminar() que devuelve un String
        when(petBffService.eliminar(anyLong())).thenReturn("Mascota eliminada correctamente");

        // Act & Assert - Se corrige la URL de /pet/delete/{id} a /{id}
        mockMvc.perform(delete("/api/bff/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}