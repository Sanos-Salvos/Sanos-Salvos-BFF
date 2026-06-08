package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.AuthClient;
import com.sanosysalvos.bff.client.LoginClient;
import com.sanosysalvos.bff.client.RegisterClient;
import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthBffServiceTest {

    @Mock
    private LoginClient loginClient;

    @Mock
    private AuthClient authClient;

    @Mock
    private RegisterClient registerClient;

    @InjectMocks
    private AuthBffService service;

    @Test
    void loginYAutenticar_deberiaRetornarAuthResponse() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("admin");
        request.setPassword("1234");

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken("jwt-token-123");
        response.setUsername("admin");
        response.setTipoToken("Bearer");
        response.setRol("ADMIN");

        when(loginClient.ejecutarLogin(any(AuthRequestDTO.class))).thenReturn(response);

        AuthResponseDTO resultado = service.loginYAutenticar(request);

        assertNotNull(resultado);
        assertEquals("jwt-token-123", resultado.getToken());
        assertEquals("admin", resultado.getUsername());
        verify(loginClient).ejecutarLogin(request);
    }

    @Test
    void loginYAutenticar_credencialesInvalidas_deberiaLanzarExcepcion() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("admin");
        request.setPassword("wrong");

        when(loginClient.ejecutarLogin(any(AuthRequestDTO.class))).thenThrow(new RuntimeException("Invalid credentials"));

        assertThrows(RuntimeException.class, () -> service.loginYAutenticar(request));
    }

    @Test
    void registrarUsuario_deberiaRetornarAuthResponse() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("nuevo@correo.com");
        request.setPassword("pass123");

        Map<String, Object> registerResult = new HashMap<>();
        registerResult.put("token", "jwt-new-token");
        registerResult.put("username", "nuevo@correo.com");
        registerResult.put("rol", "USER");

        when(registerClient.registrar(anyMap())).thenReturn(registerResult);

        AuthResponseDTO resultado = service.registrarUsuario(request);

        assertNotNull(resultado);
        assertEquals("jwt-new-token", resultado.getToken());
        assertEquals("nuevo@correo.com", resultado.getUsername());
        verify(registerClient).registrar(anyMap());
    }

    @Test
    void registrarUsuario_errorDeberiaLanzarExcepcion() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("dup@correo.com");
        request.setPassword("pass123");

        when(registerClient.registrar(anyMap())).thenThrow(new RuntimeException("Duplicate"));

        assertThrows(RuntimeException.class, () -> service.registrarUsuario(request));
    }

    @Test
    void validarFronteraToken_tokenValido_deberiaRetornarTrue() {
        when(authClient.verificarTokenValido("Bearer valid-token")).thenReturn(true);

        Boolean resultado = service.validarFronteraToken("Bearer valid-token");

        assertTrue(resultado);
    }

    @Test
    void validarFronteraToken_tokenInvalido_deberiaRetornarFalse() {
        when(authClient.verificarTokenValido("Bearer bad-token")).thenReturn(false);

        Boolean resultado = service.validarFronteraToken("Bearer bad-token");

        assertFalse(resultado);
    }

    @Test
    void validarFronteraToken_servicioCaido_deberiaLanzarExcepcion() {
        when(authClient.verificarTokenValido(any())).thenThrow(new RuntimeException("Service down"));

        assertThrows(RuntimeException.class, () -> service.validarFronteraToken("Bearer any"));
    }
}
