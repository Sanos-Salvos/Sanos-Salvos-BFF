package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.CoincidenciaBffDTO;
import com.sanosysalvos.bff.service.CoincidenciaBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bff/coincidencias")
@CrossOrigin(origins = "*")
public class CoincidenciaBffController {

    private final CoincidenciaBffService service;

    public CoincidenciaBffController(CoincidenciaBffService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CoincidenciaBffDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoincidenciaBffDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<CoincidenciaBffDTO> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstado(id, estado));
    }
}
