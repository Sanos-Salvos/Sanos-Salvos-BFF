package com.sanosysalvos.bff.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanosysalvos.bff.dto.PetBffDTO;

@FeignClient(name = "pet-client", url = "${microservicio.pet.url}")
public interface PetClient {

    @PostMapping("/api/pet/registrar")
    PetBffDTO registrarNuevaMascota(@RequestBody PetBffDTO nuevaMascota);

    @GetMapping("/api/pet/listar")
    List<PetBffDTO> listarTodas();

    @GetMapping("/api/pet/listar")
    List<PetBffDTO> listarPorOrg(@RequestParam("orgId") Long orgId);

    @GetMapping("/api/pet/{id}")
    PetBffDTO buscarPorId(@PathVariable("id") Long id);

    @PutMapping("/api/pet/actualizar/{id}")
    PetBffDTO actualizar(@PathVariable("id") Long id, @RequestBody PetBffDTO dto);

    @DeleteMapping("/api/pet/eliminar/{id}")
    void eliminar(@PathVariable("id") Long id);
}
