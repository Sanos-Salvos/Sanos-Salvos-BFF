package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.CoincidenciasBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "sanos-salvos-coincidencias", url = "${microservicio.coincidencias.url}")
public interface CoincidenciaClient {

    @PostMapping("/api/coincidencias")
    CoincidenciasBffDTO crear(@RequestBody CoincidenciasBffDTO dto);

    @GetMapping("/api/coincidencias")
    List<CoincidenciasBffDTO> listarTodas();

    @GetMapping("/api/coincidencias/{id}")
    CoincidenciasBffDTO obtenerPorId(@PathVariable("id") Long id);

    @PutMapping("/api/coincidencias/{id}/estado")
    CoincidenciasBffDTO actualizarEstado(@PathVariable("id") Long id, @RequestParam("estado") String estado);

    @DeleteMapping("/api/coincidencias/{id}")
    void eliminar(@PathVariable("id") Long id);
}