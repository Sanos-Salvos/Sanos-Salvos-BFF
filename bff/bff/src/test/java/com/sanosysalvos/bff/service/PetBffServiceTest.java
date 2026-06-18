package com.sanosysalvos.bff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sanosysalvos.bff.client.PetClient;
import com.sanosysalvos.bff.dto.PetBffDTO;

@ExtendWith(MockitoExtension.class)
class PetBffServiceTest {

    @Mock
    private PetClient petClient;

    @InjectMocks
    private PetBffService petBffService;

    @Test
    void registrar_DeberiaLlamarAlClienteYRetornarDto() {
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petClient.registrar(any(PetBffDTO.class))).thenReturn(outputDto);

        PetBffDTO resultado = petBffService.registrar(inputDto);

        assertNotNull(resultado);
        verify(petClient).registrar(inputDto);
    }

    @Test
    void listar_DeberiaRetornarListaDeMascotas() {
        List<PetBffDTO> mockList = Collections.singletonList(new PetBffDTO());
        when(petClient.listar()).thenReturn(mockList);

        List<PetBffDTO> resultado = petBffService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(petClient).listar();
    }

    @Test
    void obtenerPorId_DeberiaRetornarMascota() {
        PetBffDTO mockPet = new PetBffDTO();
        when(petClient.obtenerPorId(anyLong())).thenReturn(mockPet);

        PetBffDTO resultado = petBffService.obtenerPorId(1L);

        assertNotNull(resultado);
        verify(petClient).obtenerPorId(1L);
    }

    @Test
    void actualizar_DeberiaModificarYRetornarMascota() {
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petClient.actualizar(anyLong(), any(PetBffDTO.class))).thenReturn(outputDto);

        PetBffDTO resultado = petBffService.actualizar(1L, inputDto);

        assertNotNull(resultado);
        verify(petClient).actualizar(1L, inputDto);
    }

    @Test
    void eliminar_DeberiaLlamarAlClienteYRetornarMensaje() {
        when(petClient.eliminar(anyLong())).thenReturn("Mascota eliminada");

        String resultado = petBffService.eliminar(1L);

        assertEquals("Mascota eliminada", resultado);
        verify(petClient).eliminar(1L);
    }
}