package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.GeolocalizacionBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geolocalizacion-client", url = "${microservicio.geolocalizacion.url}")
public interface GeolocalizacionClient {
    @GetMapping("/buscar")
    GeolocalizacionBffDTO obtenerCoordenadas(@RequestParam("tipo") String tipo, @RequestParam("id") Long id);
}