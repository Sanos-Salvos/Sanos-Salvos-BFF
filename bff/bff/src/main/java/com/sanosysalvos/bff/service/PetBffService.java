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

    public List<PetBffDTO> listarTodas() {
        return petClient.obtenerTodas();
    }

    // ESTE ES EL MÉTODO QUE TE FALTABA PARA QUE NO DE ERROR
    public List<PetBffDTO> listarPorOrg(Long orgId) {
        return petClient.listarPorOrg(orgId);
    }

    public PetBffDTO obtenerDetalle(Long id) {
        return petClient.obtenerPorId(id);
    }

    public PetBffDTO registrarMascotaDesdeFront(PetBffDTO dto) {
        return petClient.registrarNuevaMascota(dto);
    }

    public PetBffDTO actualizarMascota(Long id, PetBffDTO dto) {
        return petClient.actualizarMascota(id, dto);
    }

    public void borrarMascota(Long id) {
        petClient.eliminarMascota(id);
    }
}