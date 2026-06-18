package com.sanosysalvos.bff.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import com.sanosysalvos.bff.service.AuthBffService;

@WebMvcTest(controllers = AuthBffController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthBffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthBffService authBffService;

    @Test
    void login_DeberiaRetornarOk() throws Exception {
        AuthRequestDTO request = new AuthRequestDTO();
        request.setUsername("user");
        request.setPassword("pass");

        // CORRECCIÓN: Cambiado .login() por .autenticar()
        // (Ajusta 'autenticar' si tu método en AuthBffService se llama de otra forma, ej: iniciarSesion)
        when(authBffService.loginYAutenticar(any(AuthRequestDTO.class))).thenReturn(new AuthResponseDTO());

        // NOTA: Revisa si tu ruta base en el controlador es exactamente esta o cambia a /api/auth
        mockMvc.perform(post("/api/bff/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}