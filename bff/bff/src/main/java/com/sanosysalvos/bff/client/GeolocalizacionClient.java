package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.GeolocalizacionBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sanos-salvos-geolocalizacion", url = "${microservicio.geolocalizacion.url}")
public interface GeolocalizacionClient {

    @PostMapping("/api/geolocalizacion")
    GeolocalizacionBffDTO registrar(@RequestBody GeolocalizacionBffDTO dto);

    @GetMapping("/api/geolocalizacion")
    List<GeolocalizacionBffDTO> listar();

    @GetMapping("/api/geolocalizacion/{id}")
    GeolocalizacionBffDTO obtenerPorId(@PathVariable("id") Long id);

    @DeleteMapping("/api/geolocalizacion/{id}")
    String eliminar(@PathVariable("id") Long id);
}