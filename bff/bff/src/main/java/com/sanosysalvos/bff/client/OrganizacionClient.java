package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sanos-salvos-organizaciones", url = "${microservicio.organizaciones.url}")
public interface OrganizacionClient {

    @PostMapping("/api/organizaciones")
    OrganizacionBffDTO registrarOrganizacion(@RequestBody OrganizacionBffDTO dto);

    @GetMapping("/api/organizaciones")
    List<OrganizacionBffDTO> listarTodas();

    @GetMapping("/api/organizaciones/{id}")
    OrganizacionBffDTO buscarPorId(@PathVariable("id") Long id);

    @PutMapping("/api/organizaciones/{id}")
    OrganizacionBffDTO actualizarOrganizacion(@PathVariable("id") Long id, @RequestBody OrganizacionBffDTO dto);

    @DeleteMapping("/api/organizaciones/{id}")
    String eliminarOrganizacion(@PathVariable("id") Long id);
}