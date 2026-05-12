package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.PetBffDTO;
import com.sanosysalvos.bff.service.PetBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/animales")
@CrossOrigin(origins = "*")
public class PetBffController {

    private final PetBffService service;

    public PetBffController(PetBffService service) {
        this.service = service;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PetBffDTO> ingresarMascota(@RequestBody PetBffDTO pet) {
        return ResponseEntity.ok(service.registrarMascotaDesdeFront(pet));
    }
}