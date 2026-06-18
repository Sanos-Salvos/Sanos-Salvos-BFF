package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.PetBffDTO;
import com.sanosysalvos.bff.service.PetBffService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/bff/pet")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PetBffController {

    private final PetBffService petBffService;
    public PetBffController(PetBffService petBffService) {
        this.petBffService = petBffService;
    }

    @PostMapping
    public ResponseEntity<PetBffDTO> registrar(@RequestBody PetBffDTO dto) {
        return ResponseEntity.ok(petBffService.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PetBffDTO>> listar() {
        return ResponseEntity.ok(petBffService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetBffDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(petBffService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetBffDTO> actualizar(@PathVariable Long id, @RequestBody PetBffDTO dto) {
        return ResponseEntity.ok(petBffService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(petBffService.eliminar(id));
    }
}