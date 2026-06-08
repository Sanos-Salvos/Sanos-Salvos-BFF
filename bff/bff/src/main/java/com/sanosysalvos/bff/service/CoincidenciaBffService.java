package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.CoincidenciaClient;
import com.sanosysalvos.bff.dto.CoincidenciaBffDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoincidenciaBffService {

    private final CoincidenciaClient coincidenciaClient;

    public CoincidenciaBffService(CoincidenciaClient coincidenciaClient) {
        this.coincidenciaClient = coincidenciaClient;
    }

    public List<CoincidenciaBffDTO> listarTodas() {
        return coincidenciaClient.listarTodas();
    }

    public CoincidenciaBffDTO obtenerPorId(Long id) {
        return coincidenciaClient.obtenerPorId(id);
    }

    public CoincidenciaBffDTO actualizarEstado(Long id, String estado) {
        return coincidenciaClient.actualizarEstado(id, estado);
    }
}
