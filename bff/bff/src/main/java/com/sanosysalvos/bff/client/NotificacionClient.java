package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.NotificacionBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "sanos-salvos-notificaciones", url = "${microservicio.notificaciones.url}")
public interface NotificacionClient {

    @PostMapping("/api/notificaciones/send")
    Map<String, Object> enviarNotificacion(@RequestBody NotificacionBffDTO dto);
}