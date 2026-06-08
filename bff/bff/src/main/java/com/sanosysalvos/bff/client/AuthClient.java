package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-client", url = "${microservicio.auth.url}")
public interface AuthClient {

    @PostMapping("/api/auth/login")
    AuthResponseDTO login(@RequestBody AuthRequestDTO credenciales);

    @PostMapping("/api/auth/register")
    AuthResponseDTO register(@RequestBody AuthRequestDTO usuario);

    @GetMapping("/api/auth/validar")
    boolean verificarTokenValido(@RequestHeader("Authorization") String authHeader);
}
