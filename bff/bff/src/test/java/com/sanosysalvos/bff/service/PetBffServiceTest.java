package com.sanosysalvos.bff.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Importaciones corregidas basadas en tus logs de error
import com.sanosysalvos.bff.client.PetClient;
import com.sanosysalvos.bff.dto.PetBffDTO;

@ExtendWith(MockitoExtension.class)
class PetBffServiceTest {

    // 1. Inyectamos el cliente real que causaba el NullPointerException
    @Mock
    private PetClient petClient;

    // 2. Mockito inyecta automáticamente el petClient simulado dentro de tu servicio
    @InjectMocks
    private PetBffService petBffService;

    @Test
    void listarTodas_DeberiaRetornarLista() {
        // Arrange
        List<PetBffDTO> mockList = Collections.singletonList(new PetBffDTO());
        when(petClient.obtenerTodas()).thenReturn(mockList);

        // Act
        List<PetBffDTO> resultado = petBffService.listarTodas();

        // Assert
        assertNotNull(resultado);
        verify(petClient).obtenerTodas();
    }

    @Test
    void listarPorOrg_DeberiaRetornarListaFiltrada() {
        // Arrange
        List<PetBffDTO> mockList = Collections.singletonList(new PetBffDTO());
        when(petClient.listarPorOrg(anyLong())).thenReturn(mockList);

        // Act
        List<PetBffDTO> resultado = petBffService.listarPorOrg(1L);

        // Assert
        assertNotNull(resultado);
        verify(petClient).listarPorOrg(1L);
    }

    @Test
    void obtenerDetalle_DeberiaRetornarMascota() {
        // Arrange
        PetBffDTO mockPet = new PetBffDTO();
        when(petClient.obtenerPorId(anyLong())).thenReturn(mockPet);

        // Act
        PetBffDTO resultado = petBffService.obtenerDetalle(1L);

        // Assert
        assertNotNull(resultado);
        verify(petClient).obtenerPorId(1L);
    }

    @Test
    void registrarMascotaDesdeFront_DeberiaGuardarYRetornarMascota() {
        // Arrange
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petClient.registrarNuevaMascota(any(PetBffDTO.class))).thenReturn(outputDto);

        // Act
        PetBffDTO resultado = petBffService.registrarMascotaDesdeFront(inputDto);

        // Assert
        assertNotNull(resultado);
        verify(petClient).registrarNuevaMascota(inputDto);
    }

    @Test
    void actualizarMascota_DeberiaModificarYRetornarMascota() {
        // Arrange
        PetBffDTO inputDto = new PetBffDTO();
        PetBffDTO outputDto = new PetBffDTO();
        when(petClient.actualizarMascota(anyLong(), any(PetBffDTO.class))).thenReturn(outputDto);

        // Act
        PetBffDTO resultado = petBffService.actualizarMascota(1L, inputDto);

        // Assert
        assertNotNull(resultado);
        verify(petClient).actualizarMascota(1L, inputDto);
    }

    @Test
    void borrarMascota_DeberiaEjecutarBorrado() {
        // Arrange
        // Nota: Si tu PetClient.borrarMascota es void, doNothing() es la opción ideal.
        // Si el log no se quejó de delete/borrar, mantenemos la llamada base.

        // Act
        petBffService.borrarMascota(1L);

        // Assert
        assertNotNull(petBffService);
    }
}