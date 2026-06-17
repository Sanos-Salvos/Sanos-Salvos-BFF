package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.DashboardResumenDTO;
import com.sanosysalvos.bff.service.DashboardBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardBffController {

    private final DashboardBffService dashboardBffService;
    public DashboardBffController(DashboardBffService dashboardBffService) {
        this.dashboardBffService = dashboardBffService;
    }

    @GetMapping
    public ResponseEntity<DashboardResumenDTO> getDashboard(
            @RequestParam(value = "usuarioId", required = false) Long usuarioId,
            @RequestParam(value = "organizacionId", required = false) Long organizacionId) {

        DashboardResumenDTO dashboard = dashboardBffService.construirDashboardCompleto(usuarioId, organizacionId);

        return ResponseEntity.ok(dashboard);
    }
}