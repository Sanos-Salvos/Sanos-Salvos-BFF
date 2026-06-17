package com.sanosysalvos.bff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Importaciones de tus clientes externos y DTOs basados en tus logs
import com.sanosysalvos.bff.client.AuthClient;
import com.sanosysalvos.bff.client.LoginClient;
import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;

@ExtendWith(MockitoExtension.class)
class AuthBffServiceTest {

    // 1. Inyectamos los Mocks de los clientes HTTP que descubrimos en el log
    @Mock
    private LoginClient loginClient;

    @Mock
    private AuthClient authClient;

    // 2. Mockito meterá automáticamente ambos clientes dentro de tu servicio real
    @InjectMocks
    private AuthBffService authBffService;

    @Test
    void loginYAutenticar_DeberiaRetornarResponseDTO() {
        // Arrange
        AuthRequestDTO request = new AuthRequestDTO();
        AuthResponseDTO expectedResponse = new AuthResponseDTO();

        // Simulamos el comportamiento del cliente de Login interno
        when(loginClient.ejecutarLogin(any(AuthRequestDTO.class))).thenReturn(expectedResponse);

        // Act
        AuthResponseDTO actualResponse = authBffService.loginYAutenticar(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void validarFronteraToken_DeberiaRetornarTrue() {
        // Arrange
        String token = "Bearer token-de-prueba-123";

        // Simulamos el comportamiento del cliente de Autorización interno
        when(authClient.verificarTokenValido(anyString())).thenReturn(true);

        // Act
        Boolean resultado = authBffService.validarFronteraToken(token);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado);
    }
}