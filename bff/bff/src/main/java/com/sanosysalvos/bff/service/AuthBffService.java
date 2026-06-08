package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.AuthClient;
import com.sanosysalvos.bff.client.LoginClient;
import com.sanosysalvos.bff.client.RegisterClient;
import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthBffService {

    private final LoginClient loginClient;
    private final AuthClient authClient;
    private final RegisterClient registerClient;

    public AuthBffService(LoginClient loginClient, AuthClient authClient, RegisterClient registerClient) {
        this.loginClient = loginClient;
        this.authClient = authClient;
        this.registerClient = registerClient;
    }

    public AuthResponseDTO loginYAutenticar(AuthRequestDTO credenciales) {
        return loginClient.ejecutarLogin(credenciales);
    }

    public AuthResponseDTO registrarUsuario(AuthRequestDTO usuario) {
        Map<String, Object> registerRequest = new HashMap<>();
        registerRequest.put("username", usuario.getUsername());
        registerRequest.put("email", usuario.getUsername());
        registerRequest.put("password", usuario.getPassword());
        Map<String, Object> result = registerClient.registrar(registerRequest);
        AuthResponseDTO response = new AuthResponseDTO();
        response.setUsername((String) result.getOrDefault("username", usuario.getUsername()));
        response.setToken((String) result.getOrDefault("token", ""));
        response.setTipoToken("Bearer");
        response.setRol((String) result.getOrDefault("rol", "USER"));
        return response;
    }

    public boolean validarFronteraToken(String authHeader) {
        try {
            return authClient.verificarTokenValido(authHeader);
        } catch (Exception e) {
            throw new RuntimeException("Error validando token: " + e.getMessage());
        }
    }
}
