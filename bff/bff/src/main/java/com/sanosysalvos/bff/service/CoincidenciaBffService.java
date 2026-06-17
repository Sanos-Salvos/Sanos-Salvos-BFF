package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.CoincidenciaClient;
import com.sanosysalvos.bff.dto.CoincidenciasBffDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoincidenciaBffService {

    private final CoincidenciaClient coincidenciaClient;

    public CoincidenciaBffService(CoincidenciaClient coincidenciaClient) {
        this.coincidenciaClient = coincidenciaClient;
    }

    public CoincidenciasBffDTO crear(CoincidenciasBffDTO dto) {
        return coincidenciaClient.crear(dto);
    }

    public List<CoincidenciasBffDTO> listarTodas() {
        return coincidenciaClient.listarTodas();
    }

    public CoincidenciasBffDTO obtenerPorId(Long id) {
        return coincidenciaClient.obtenerPorId(id);
    }

    public CoincidenciasBffDTO actualizarEstado(Long id, String estado) {
        return coincidenciaClient.actualizarEstado(id, estado);
    }

    public void eliminar(Long id) {
        coincidenciaClient.eliminar(id);
    }
}