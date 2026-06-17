package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.PetBffDTO;
import com.sanosysalvos.bff.service.PetBffService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin; // Asegúrate de que este import esté

@CrossOrigin(origins = "*", allowedHeaders = "*")

import java.util.List;

@RestController
@RequestMapping("/api/bff/pet")
@CrossOrigin(origins = "*")
public class PetBffController {

    private final PetBffService petBffService;

    public PetBffController(PetBffService petBffService) {
        this.petBffService = petBffService;
    }

    @GetMapping("/list")
    public List<PetBffDTO> list() {
        return petBffService.listarTodas();
    }

    // NUEVO: Este es el que usa el método que daba error
    @GetMapping("/organizacion/{orgId}")
    public List<PetBffDTO> listByOrg(@PathVariable Long orgId) {
        return petBffService.listarPorOrg(orgId);
    }

    @GetMapping("/{id}")
    public PetBffDTO getById(@PathVariable Long id) {
        return petBffService.obtenerDetalle(id);
    }

    @PostMapping("/create")
    public PetBffDTO create(@RequestBody PetBffDTO dto) {
        return petBffService.registrarMascotaDesdeFront(dto);
    }

    @PutMapping("/update/{id}")
    public PetBffDTO update(@PathVariable Long id, @RequestBody PetBffDTO dto) {
        return petBffService.actualizarMascota(id, dto);
    }

    @PostMapping
    public ResponseEntity<PetBffDTO> registrar(@RequestBody PetBffDTO dto) {
        return ResponseEntity.ok(service.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PetBffDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetBffDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetBffDTO> actualizar(@PathVariable Long id, @RequestBody PetBffDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(service.eliminar(id));
    }
}