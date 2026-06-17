package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.OrganizacionClient;
import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacionBffService {

    private final OrganizacionClient client;

    public OrganizacionBffService(OrganizacionClient client) {
        this.client = client;
    }

    public OrganizacionBffDTO registrar(OrganizacionBffDTO dto) {
        return client.registrarOrganizacion(dto);
    }

    public List<OrganizacionBffDTO> listar() {
        return client.listarTodas();
    }

    public OrganizacionBffDTO obtenerPorId(Long id) {
        return client.buscarPorId(id);
    }

    public OrganizacionBffDTO actualizar(Long id, OrganizacionBffDTO dto) {
        return client.actualizarOrganizacion(id, dto);
    }

    public String eliminar(Long id) {
        return client.eliminarOrganizacion(id);
    }
}