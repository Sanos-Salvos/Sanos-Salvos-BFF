package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.CoincidenciaBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "coincidencias-client", url = "${microservicio.coincidencias.url}")
public interface CoincidenciaClient {
    @GetMapping("/listar-por-organizacion")
    List<CoincidenciaBffDTO> obtenerCoincidencias(@RequestParam("organizacionId") Long organizacionId);
}