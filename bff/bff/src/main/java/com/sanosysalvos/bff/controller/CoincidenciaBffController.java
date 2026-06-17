package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.CoincidenciasBffDTO;
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

    @PostMapping
    public ResponseEntity<CoincidenciasBffDTO> crear(@RequestBody CoincidenciasBffDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<CoincidenciasBffDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoincidenciasBffDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<CoincidenciasBffDTO> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}