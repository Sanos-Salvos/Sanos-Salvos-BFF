package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.OrganizacionClient;
import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacionBffService {

    private final OrganizacionClient organizacionClient;

    public OrganizacionBffService(OrganizacionClient organizacionClient) {
        this.organizacionClient = organizacionClient;
    }

    public List<OrganizacionBffDTO> listarTodas() {
        return organizacionClient.listarTodas();
    }

    public OrganizacionBffDTO buscarPorId(Long id) {
        return organizacionClient.obtenerPorId(id);
    }

    public OrganizacionBffDTO crear(OrganizacionBffDTO org) {
        return organizacionClient.crear(org);
    }

    public OrganizacionBffDTO actualizar(Long id, OrganizacionBffDTO org) {
        return organizacionClient.actualizar(id, org);
    }

    public void eliminar(Long id) {
        organizacionClient.eliminar(id);
    }
}
