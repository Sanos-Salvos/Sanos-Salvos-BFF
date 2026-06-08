package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "login-client", url = "${microservicio.auth.url}")
public interface LoginClient {

    @PostMapping("/api/auth/login")
    AuthResponseDTO ejecutarLogin(@RequestBody AuthRequestDTO credenciales);
}
