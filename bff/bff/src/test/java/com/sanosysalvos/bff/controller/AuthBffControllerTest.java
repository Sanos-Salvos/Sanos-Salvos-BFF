package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import com.sanosysalvos.bff.service.AuthBffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthBffControllerTest {

    @Mock
    private AuthBffService service;

    @InjectMocks
    private AuthBffController controller;

    @Test
    void procesarIngreso_credencialesValidas_deberiaRetornarToken() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("admin");
        request.setPassword("1234");

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken("jwt-token");
        response.setUsername("admin");
        response.setTipoToken("Bearer");
        response.setRol("ADMIN");

        when(service.loginYAutenticar(any(AuthRequestDTO.class))).thenReturn(response);

        ResponseEntity<AuthResponseDTO> resultado = controller.procesarIngreso(request);

        assertEquals(200, resultado.getStatusCode().value());
        assertEquals("jwt-token", resultado.getBody().getToken());
    }

    @Test
    void procesarIngreso_credencialesInvalidas_deberiaLanzarExcepcion() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("admin");
        request.setPassword("wrong");

        when(service.loginYAutenticar(any())).thenThrow(new RuntimeException("Invalid credentials"));

        assertThrows(RuntimeException.class, () -> controller.procesarIngreso(request));
    }

    @Test
    void registrarUsuario_deberiaRetornarAuthResponse() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("nuevo@correo.com");
        request.setPassword("pass123");

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken("jwt-new");
        response.setUsername("nuevo@correo.com");

        when(service.registrarUsuario(any(AuthRequestDTO.class))).thenReturn(response);

        ResponseEntity<AuthResponseDTO> resultado = controller.registrarUsuario(request);

        assertEquals(200, resultado.getStatusCode().value());
        assertEquals("jwt-new", resultado.getBody().getToken());
    }

    @Test
    void registrarUsuario_errorDeberiaLanzarExcepcion() {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("dup@correo.com");
        request.setPassword("pass");

        when(service.registrarUsuario(any())).thenThrow(new RuntimeException("Duplicate"));

        assertThrows(RuntimeException.class, () -> controller.registrarUsuario(request));
    }

    @Test
    void chequearToken_tokenValido_deberiaRetornarTrue() {
        when(service.validarFronteraToken("Bearer valid-token")).thenReturn(true);

        ResponseEntity<Boolean> resultado = controller.chequearToken("Bearer valid-token");

        assertEquals(200, resultado.getStatusCode().value());
        assertTrue(resultado.getBody());
    }

    @Test
    void chequearToken_tokenInvalido_deberiaRetornarFalse() {
        when(service.validarFronteraToken("Bearer bad-token")).thenReturn(false);

        ResponseEntity<Boolean> resultado = controller.chequearToken("Bearer bad-token");

        assertFalse(resultado.getBody());
    }

    @Test
    void chequearToken_servicioCaido_deberiaLanzarExcepcion() {
        when(service.validarFronteraToken(any())).thenThrow(new RuntimeException("Service down"));

        assertThrows(RuntimeException.class, () -> controller.chequearToken("Bearer any"));
    }
}
