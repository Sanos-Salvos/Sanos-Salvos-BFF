package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.AuthClient;
import com.sanosysalvos.bff.client.LoginClient;
import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthBffService {

    private final LoginClient loginClient;
    private final AuthClient authClient;

    public AuthBffService(LoginClient loginClient, AuthClient authClient) {
        this.loginClient = loginClient;
        this.authClient = authClient;
    }

    public AuthResponseDTO loginYAutenticar(AuthRequestDTO credenciales) {
        return loginClient.ejecutarLogin(credenciales);
    }

    public Boolean validarFronteraToken(String token) {
        return authClient.verificarTokenValido(token);
    }
}