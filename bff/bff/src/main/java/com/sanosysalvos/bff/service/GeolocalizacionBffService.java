package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.GeolocalizacionClient;
import com.sanosysalvos.bff.dto.GeolocalizacionBffDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeolocalizacionBffService {

    private final GeolocalizacionClient client;

    public GeolocalizacionBffService(GeolocalizacionClient client) {
        this.client = client;
    }

    public GeolocalizacionBffDTO registrar(GeolocalizacionBffDTO dto) {
        return client.registrar(dto);
    }

    public List<GeolocalizacionBffDTO> listar() {
        return client.listar();
    }

    public GeolocalizacionBffDTO obtenerPorId(Long id) {
        return client.obtenerPorId(id);
    }

    public String eliminar(Long id) {
        return client.eliminar(id);
    }
}