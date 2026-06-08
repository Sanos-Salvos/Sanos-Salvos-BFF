package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.PetClient;
import com.sanosysalvos.bff.dto.PetBffDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetBffServiceTest {

    @Mock
    private PetClient petClient;

    @InjectMocks
    private PetBffService service;

    private PetBffDTO buildPet() {
        return PetBffDTO.builder()
                .id(1L).nombre("Firulais").especie("Perro").raza("Labrador")
                .estado("PERDIDO").comuna("Santiago").contacto("12345678")
                .build();
    }

    @Test
    void registrarMascotaDesdeFront_deberiaRetornarDTO() {
        PetBffDTO input = buildPet();
        input.setId(null);
        PetBffDTO saved = buildPet();

        when(petClient.registrarNuevaMascota(any(PetBffDTO.class))).thenReturn(saved);

        PetBffDTO resultado = service.registrarMascotaDesdeFront(input);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Firulais", resultado.getNombre());
        verify(petClient).registrarNuevaMascota(input);
    }

    @Test
    void listarTodas_deberiaRetornarLista() {
        when(petClient.listarTodas()).thenReturn(List.of(buildPet()));

        List<PetBffDTO> resultado = service.listarTodas();

        assertEquals(1, resultado.size());
        verify(petClient).listarTodas();
    }

    @Test
    void buscarPorId_existente_deberiaRetornarDTO() {
        when(petClient.buscarPorId(1L)).thenReturn(buildPet());

        PetBffDTO resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Firulais", resultado.getNombre());
    }

    @Test
    void buscarPorId_noExistente_deberiaLanzarExcepcion() {
        when(petClient.buscarPorId(99L)).thenThrow(new RuntimeException("Not found"));

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void actualizar_deberiaRetornarDTOActualizado() {
        PetBffDTO update = buildPet();
        update.setNombre("Firulais II");

        when(petClient.actualizar(eq(1L), any(PetBffDTO.class))).thenReturn(update);

        PetBffDTO resultado = service.actualizar(1L, update);

        assertEquals("Firulais II", resultado.getNombre());
        verify(petClient).actualizar(1L, update);
    }

    @Test
    void eliminar_deberiaLlamarPetClient() {
        doNothing().when(petClient).eliminar(1L);

        service.eliminar(1L);

        verify(petClient).eliminar(1L);
    }
}
