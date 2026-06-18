package com.sanosysalvos.bff.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import java.util.Map;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;
    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
        this.mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(exceptionHandler)
                .build();
    }

    @Test
    void cuandoLanzaCallNotPermittedException_DeberiaRetornarServiceUnavailable() throws Exception {
        // Arrange - Configuramos el mock para evitar el NullPointerException interno de Resilience4j
        CircuitBreaker circuitBreaker = mock(CircuitBreaker.class);
        CircuitBreakerConfig config = mock(CircuitBreakerConfig.class);

        when(circuitBreaker.getCircuitBreakerConfig()).thenReturn(config);
        when(config.isWritableStackTraceEnabled()).thenReturn(true);

        CallNotPermittedException ex = CallNotPermittedException.createCallNotPermittedException(circuitBreaker);

        // Act
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleCircuitBreakerOpen(ex);

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        org.junit.jupiter.api.Assertions.assertEquals("Servicio Degradado Temporalmente (BFF)", response.getBody().get("error"));
    }

    @Test
    void cuandoLanzaRuntimeException_DeberiaRetornarBadRequest() throws Exception {
        mockMvc.perform(get("/test/runtime"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Error en la Orquestación del BFF"))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void cuandoLanzaGenericException_DeberiaRetornarInternalServerError() throws Exception {
        mockMvc.perform(get("/test/generic"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Error Crítico en el BFF (500)"))
                .andExpect(jsonPath("$.status").value(500));
    }

    @RestController
    static class TestController {

        @GetMapping("/test/runtime")
        public void throwRuntime() {
            throw new RuntimeException("Fallo controlado en runtime");
        }

        @GetMapping("/test/generic")
        public void throwGeneric() throws Exception {
            throw new Exception("Error general del sistema");
        }
    }
}