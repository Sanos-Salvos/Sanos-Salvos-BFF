package com.sanosysalvos.bff.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleRuntimeException_DeberiaRetornarBadRequest() {
        // Arrange
        RuntimeException exception = new RuntimeException("Error inesperado en el BFF");

        // Act
        // Usamos el comodín '?' para evitar conflictos de tipos con el retorno del Handler real
        ResponseEntity<?> response = exceptionHandler.handleRuntimeException(exception);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}