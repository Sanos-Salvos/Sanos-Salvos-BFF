package com.sanosysalvos.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "register-client", url = "${microservicio.register.url}")
public interface RegisterClient {

    @PostMapping("/api/register")
    Map<String, Object> registrar(@RequestBody Map<String, Object> usuario);
}
