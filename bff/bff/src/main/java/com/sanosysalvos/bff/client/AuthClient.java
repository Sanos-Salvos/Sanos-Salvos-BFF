package com.sanosysalvos.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-client", url = "${microservicio.auth.url}")
public interface AuthClient {
    @GetMapping("/validar")
    Boolean verificarTokenValido(@RequestHeader("Authorization") String tokenCompleto);
}