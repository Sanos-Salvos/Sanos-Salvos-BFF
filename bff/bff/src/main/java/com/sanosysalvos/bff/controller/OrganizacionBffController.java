package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import com.sanosysalvos.bff.service.OrganizacionBffService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<OrganizacionBffDTO> registrarOrganizacion(@RequestBody OrganizacionBffDTO dto) {
        return new ResponseEntity<>(service.registrar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizacionBffDTO>> listarTodas() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacionBffDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacionBffDTO> actualizarOrganizacion(@PathVariable Long id, @RequestBody OrganizacionBffDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOrganizacion(@PathVariable Long id) {
        return ResponseEntity.ok(service.eliminar(id));
    }
}