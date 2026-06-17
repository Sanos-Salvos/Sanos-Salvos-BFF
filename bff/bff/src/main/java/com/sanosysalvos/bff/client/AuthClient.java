package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "sanos-salvos-auth", url = "${microservicio.auth.url}")
public interface AuthClient {

    @PostMapping("/api/auth/login")
    AuthResponseDTO login(@RequestBody AuthRequestDTO credenciales);

    @PostMapping("/api/auth/register")
    AuthResponseDTO register(@RequestBody AuthRequestDTO usuario);
}