package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.PetBffDTO;
import com.sanosysalvos.bff.service.PetBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/listar")
    public ResponseEntity<List<PetBffDTO>> listarMascotas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetBffDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PetBffDTO> actualizarMascota(@PathVariable Long id, @RequestBody PetBffDTO pet) {
        return ResponseEntity.ok(service.actualizar(id, pet));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarMascota(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Mascota eliminada correctamente");
    }
}
