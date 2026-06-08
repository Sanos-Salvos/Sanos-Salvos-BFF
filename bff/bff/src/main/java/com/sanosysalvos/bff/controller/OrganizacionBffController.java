package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import com.sanosysalvos.bff.service.OrganizacionBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bff/organizaciones")
@CrossOrigin(origins = "*")
public class OrganizacionBffController {

    private final OrganizacionBffService service;

    public OrganizacionBffController(OrganizacionBffService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OrganizacionBffDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OrganizacionBffDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<OrganizacionBffDTO> crear(@RequestBody OrganizacionBffDTO org) {
        return ResponseEntity.ok(service.crear(org));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OrganizacionBffDTO> actualizar(@PathVariable Long id, @RequestBody OrganizacionBffDTO org) {
        return ResponseEntity.ok(service.actualizar(id, org));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Organización eliminada correctamente");
    }
}
