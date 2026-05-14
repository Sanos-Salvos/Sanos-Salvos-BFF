package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.PetBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "pet-service", url = "${microservicio.pet.url}")
public interface PetClient {

    @GetMapping("/all") // Asegúrate que el micro Pet tenga esta ruta
    List<PetBffDTO> obtenerTodas();

    @GetMapping("/{id}")
    PetBffDTO obtenerPorId(@PathVariable("id") Long id);

    @PostMapping("/create")
    PetBffDTO registrarNuevaMascota(@RequestBody PetBffDTO dto);

    @PutMapping("/update/{id}")
    PetBffDTO actualizarMascota(@PathVariable("id") Long id, @RequestBody PetBffDTO dto);

    @DeleteMapping("/delete/{id}")
    void eliminarMascota(@PathVariable("id") Long id);

    @GetMapping("/organizacion/{orgId}")
    List<PetBffDTO> listarPorOrg(@PathVariable("orgId") Long orgId);
}