package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.NotificacionBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "notificaciones-client", url = "${microservicio.notificaciones.url}")
public interface NotificacionClient {
    @GetMapping("/bandeja/{usuarioId}")
    List<NotificacionBffDTO> obtenerAlertasUsuario(@PathVariable("usuarioId") Long usuarioId);
}