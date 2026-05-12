package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "login-client", url = "${microservicio.login.url}")
public interface LoginClient {
    @PostMapping("/ingresar")
    AuthResponseDTO ejecutarLogin(@RequestBody AuthRequestDTO credenciales);
}