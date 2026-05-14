package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.PetBffDTO;
import com.sanosysalvos.bff.service.PetBffService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin; // Asegúrate de que este import esté

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping("/api/bff/pet")
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

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        petBffService.borrarMascota(id);
    }
}