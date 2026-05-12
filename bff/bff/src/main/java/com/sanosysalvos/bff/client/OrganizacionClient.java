package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "organizaciones-client", url = "${microservicio.organizaciones.url}")
public interface OrganizacionClient {
    @GetMapping("/buscar/{id}")
    OrganizacionBffDTO obtenerPorId(@PathVariable("id") Long id);
}