package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.*;
import com.sanosysalvos.bff.dto.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class DashboardBffService {

    private final UsuarioClient usuarioClient;
    private final OrganizacionClient organizacionClient;
    private final GeolocalizacionClient geolocalizacionClient;
    private final PetClient petClient;
    private final CoincidenciaClient coincidenciaClient;
    private final NotificacionClient notificacionClient;

    public DashboardBffService(UsuarioClient usuarioClient, OrganizacionClient organizacionClient,
                               GeolocalizacionClient geolocalizacionClient, PetClient petClient,
                               CoincidenciaClient coincidenciaClient, NotificacionClient notificacionClient) {
        this.usuarioClient = usuarioClient;
        this.organizacionClient = organizacionClient;
        this.geolocalizacionClient = geolocalizacionClient;
        this.petClient = petClient;
        this.coincidenciaClient = coincidenciaClient;
        this.notificacionClient = notificacionClient;
    }

    public DashboardResumenDTO construirDashboardCompleto(Long usuarioId, Long organizacionId) {
        DashboardResumenDTO dto = new DashboardResumenDTO();

        try { dto.setUsuarioLogueado(usuarioClient.obtenerPerfilUsuario(usuarioId)); } catch(Exception e) { dto.setUsuarioLogueado(null); }
        try { dto.setOrganizacionAsociada(organizacionClient.obtenerPorId(organizacionId)); } catch(Exception e) { dto.setOrganizacionAsociada(null); }
        try { dto.setUbicacionMapa(geolocalizacionClient.obtenerCoordenadas("ORGANIZACION", organizacionId)); } catch(Exception e) { dto.setUbicacionMapa(null); }
        try { dto.setListaMascotas(petClient.listarPorOrg(organizacionId)); } catch(Exception e) { dto.setListaMascotas(new ArrayList<>()); }

        try {
            var coincidencias = coincidenciaClient.obtenerCoincidencias(organizacionId);
            dto.setCoincidenciasCriticas(coincidencias);
            dto.setContadorAlertasPendientes(coincidencias.size());
        } catch(Exception e) {
            dto.setCoincidenciasCriticas(new ArrayList<>());
            dto.setContadorAlertasPendientes(0);
        }

        try { dto.setBuzonNotificaciones(notificacionClient.obtenerAlertasUsuario(usuarioId)); } catch(Exception e) { dto.setBuzonNotificaciones(new ArrayList<>()); }

        return dto;
    }
}