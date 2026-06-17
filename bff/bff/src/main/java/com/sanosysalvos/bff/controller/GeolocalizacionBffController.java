package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.GeolocalizacionBffDTO;
import com.sanosysalvos.bff.service.GeolocalizacionBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bff/geolocalizacion")
@CrossOrigin(origins = "*")
public class GeolocalizacionBffController {

    private final GeolocalizacionBffService service;

    public GeolocalizacionBffController(GeolocalizacionBffService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GeolocalizacionBffDTO> registrar(@RequestBody GeolocalizacionBffDTO dto) {
        return ResponseEntity.ok(service.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<GeolocalizacionBffDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeolocalizacionBffDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(service.eliminar(id));
    }
}