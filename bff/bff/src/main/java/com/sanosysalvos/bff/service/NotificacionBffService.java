package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.NotificacionClient;
import com.sanosysalvos.bff.dto.NotificacionBffDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificacionBffService {

    private final NotificacionClient client;

    public NotificacionBffService(NotificacionClient client) {
        this.client = client;
    }

    public Map<String, Object> enviar(NotificacionBffDTO dto) {
        return client.enviarNotificacion(dto);
    }
}