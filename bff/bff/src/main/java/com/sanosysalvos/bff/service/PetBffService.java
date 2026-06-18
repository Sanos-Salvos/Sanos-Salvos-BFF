package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.PetClient;
import com.sanosysalvos.bff.dto.PetBffDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetBffService {

    private final PetClient client;

    public PetBffService(PetClient client) {
        this.client = client;
    }

    public PetBffDTO registrar(PetBffDTO dto) {
        return client.registrar(dto);
    }

    public List<PetBffDTO> listar() {
        return client.listar();
    }

    public PetBffDTO obtenerPorId(Long id) {
        return client.obtenerPorId(id);
    }

    public PetBffDTO actualizar(Long id, PetBffDTO dto) {
        return client.actualizar(id, dto);
    }

    public String eliminar(Long id) {
        return client.eliminar(id);
    }
}