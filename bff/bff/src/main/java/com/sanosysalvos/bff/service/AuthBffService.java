package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.AuthClient;
import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthBffService {

    private final AuthClient authClient;

    public AuthBffService(AuthClient authClient) {
        this.authClient = authClient;
    }

    public AuthResponseDTO loginYAutenticar(AuthRequestDTO credenciales) {
        return authClient.login(credenciales);
    }

    public AuthResponseDTO registrarUsuario(AuthRequestDTO usuario) {
        return authClient.register(usuario);
    }

    public boolean validarFronteraToken(String authHeader) {
        if (authHeader == null || authHeader.isBlank()) {
            return false;
        }

        try {
            return authHeader.startsWith("Bearer ");
        } catch (Exception e) {
            return false;
        }
    }
}