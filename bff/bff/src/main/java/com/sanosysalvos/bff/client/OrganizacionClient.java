package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "organizaciones-client", url = "${microservicio.organizaciones.url}")
public interface OrganizacionClient {

    @GetMapping("/buscar/{id}")
    OrganizacionBffDTO obtenerPorId(@PathVariable("id") Long id);

    @GetMapping("/listar")
    List<OrganizacionBffDTO> listarTodas();

    @PostMapping("/crear")
    OrganizacionBffDTO crear(@RequestBody OrganizacionBffDTO org);

    @PutMapping("/actualizar/{id}")
    OrganizacionBffDTO actualizar(@PathVariable("id") Long id, @RequestBody OrganizacionBffDTO org);

    @DeleteMapping("/eliminar/{id}")
    void eliminar(@PathVariable("id") Long id);
}
