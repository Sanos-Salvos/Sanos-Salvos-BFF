package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.PetBffDTO;
import com.sanosysalvos.bff.service.PetBffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetBffControllerTest {

    @Mock
    private PetBffService service;

    @InjectMocks
    private PetBffController controller;

    private PetBffDTO buildPet() {
        return PetBffDTO.builder()
                .id(1L).nombre("Firulais").especie("Perro").raza("Labrador")
                .estado("PERDIDO").comuna("Santiago").contacto("12345678")
                .build();
    }

    @Test
    void ingresarMascota_deberiaRetornarDTO() {
        PetBffDTO input = buildPet();
        input.setId(null);
        when(service.registrarMascotaDesdeFront(any(PetBffDTO.class))).thenReturn(buildPet());

        ResponseEntity<PetBffDTO> response = controller.ingresarMascota(input);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Firulais", response.getBody().getNombre());
    }

    @Test
    void listarMascotas_deberiaRetornarLista() {
        when(service.listarTodas()).thenReturn(List.of(buildPet()));

        ResponseEntity<List<PetBffDTO>> response = controller.listarMascotas();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void obtenerPorId_existente_deberiaRetornarDTO() {
        when(service.buscarPorId(1L)).thenReturn(buildPet());

        ResponseEntity<PetBffDTO> response = controller.obtenerPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Firulais", response.getBody().getNombre());
    }

    @Test
    void actualizarMascota_deberiaRetornarDTOActualizado() {
        PetBffDTO update = buildPet();
        update.setNombre("Firulais II");
        when(service.actualizar(eq(1L), any(PetBffDTO.class))).thenReturn(update);

        ResponseEntity<PetBffDTO> response = controller.actualizarMascota(1L, update);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Firulais II", response.getBody().getNombre());
    }

    @Test
    void eliminarMascota_deberiaRetornarMensaje() {
        doNothing().when(service).eliminar(1L);

        ResponseEntity<String> response = controller.eliminarMascota(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Mascota eliminada correctamente", response.getBody());
        verify(service).eliminar(1L);
    }
}
