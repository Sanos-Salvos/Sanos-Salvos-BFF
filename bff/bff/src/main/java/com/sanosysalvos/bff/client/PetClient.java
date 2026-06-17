package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.PetBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sanos-salvos-pet", url = "${microservicio.pet.url}")
public interface PetClient {

    @PostMapping("/api/pet")
    PetBffDTO registrar(@RequestBody PetBffDTO dto);

    @GetMapping("/api/pet")
    List<PetBffDTO> listar();

    @GetMapping("/api/pet/{id}")
    PetBffDTO obtenerPorId(@PathVariable("id") Long id);

    @PutMapping("/api/pet/{id}")
    PetBffDTO actualizar(@PathVariable("id") Long id, @RequestBody PetBffDTO dto);

    @DeleteMapping("/api/pet/{id}")
    String eliminar(@PathVariable("id") Long id);
}