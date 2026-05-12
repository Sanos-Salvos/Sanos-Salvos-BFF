package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.DashboardResumenDTO;
import com.sanosysalvos.bff.service.DashboardBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/main")
@CrossOrigin(origins = "*")
public class DashboardBffController {

    private final DashboardBffService service;

    public DashboardBffController(DashboardBffService service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResumenDTO> verPantallaCompleta(@RequestParam Long usuarioId, @RequestParam Long organizacionId) {
        return ResponseEntity.ok(service.construirDashboardCompleto(usuarioId, organizacionId));
    }
}