package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.CoincidenciaBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "coincidencias-client", url = "${microservicio.coincidencias.url}")
public interface CoincidenciaClient {

    @GetMapping("/api/coincidencias")
    List<CoincidenciaBffDTO> listarTodas();

    @GetMapping("/api/coincidencias/{id}")
    CoincidenciaBffDTO obtenerPorId(@PathVariable("id") Long id);

    @GetMapping("/api/coincidencias")
    List<CoincidenciaBffDTO> obtenerCoincidencias(@RequestParam("organizacionId") Long organizacionId);

    @PutMapping("/api/coincidencias/{id}/estado")
    CoincidenciaBffDTO actualizarEstado(@PathVariable("id") Long id, @RequestParam("estado") String estado);
}
