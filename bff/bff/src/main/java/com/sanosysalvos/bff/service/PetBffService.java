package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.PetClient;
import com.sanosysalvos.bff.dto.PetBffDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetBffService {

    private final PetClient petClient;

    public PetBffService(PetClient petClient) {
        this.petClient = petClient;
    }

    public PetBffDTO registrarMascotaDesdeFront(PetBffDTO dto) {
        return petClient.registrarNuevaMascota(dto);
    }

    public List<PetBffDTO> listarTodas() {
        return petClient.listarTodas();
    }

    public PetBffDTO buscarPorId(Long id) {
        return petClient.buscarPorId(id);
    }

    public PetBffDTO actualizar(Long id, PetBffDTO dto) {
        return petClient.actualizar(id, dto);
    }

    public void eliminar(Long id) {
        petClient.eliminar(id);
    }
}
