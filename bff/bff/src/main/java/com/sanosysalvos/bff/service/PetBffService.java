package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.PetClient;
import com.sanosysalvos.bff.dto.PetBffDTO;
import org.springframework.stereotype.Service;

@Service
public class PetBffService {

    private final PetClient petClient;

    public PetBffService(PetClient petClient) {
        this.petClient = petClient;
    }

    public PetBffDTO registrarMascotaDesdeFront(PetBffDTO dto) {
        return petClient.registrarNuevaMascota(dto);
    }
}