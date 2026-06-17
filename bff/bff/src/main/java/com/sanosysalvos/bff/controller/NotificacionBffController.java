package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.NotificacionBffDTO;
import com.sanosysalvos.bff.service.NotificacionBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bff/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionBffController {

    private final NotificacionBffService service;

    public NotificacionBffController(NotificacionBffService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> send(@RequestBody NotificacionBffDTO dto) {
        return ResponseEntity.ok(service.enviar(dto));
    }
}