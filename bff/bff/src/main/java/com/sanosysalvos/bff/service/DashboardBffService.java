package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.*;
import com.sanosysalvos.bff.dto.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardBffService {

    private final AuthClient authClient;
    private final OrganizacionClient organizacionClient;
    private final GeolocalizacionClient geolocalizacionClient;
    private final PetClient petClient;
    private final CoincidenciaClient coincidenciaClient;
    private final NotificacionClient notificacionClient;

    public DashboardBffService(AuthClient authClient, OrganizacionClient organizacionClient,
                               GeolocalizacionClient geolocalizacionClient, PetClient petClient,
                               CoincidenciaClient coincidenciaClient, NotificacionClient notificacionClient) {
        this.authClient = authClient;
        this.organizacionClient = organizacionClient;
        this.geolocalizacionClient = geolocalizacionClient;
        this.petClient = petClient;
        this.coincidenciaClient = coincidenciaClient;
        this.notificacionClient = notificacionClient;
    }

    public DashboardResumenDTO construirDashboardCompleto(Long usuarioId, Long organizacionId) {
        DashboardResumenDTO dto = new DashboardResumenDTO();

        if (usuarioId != null) {
            AuthResponseDTO perfil = new AuthResponseDTO();
            perfil.setAutenticado(true);
            perfil.setUsername("Usuario_" + usuarioId);
            perfil.setRol(organizacionId != null ? "ORGANIZACION" : "USER");
            dto.setUsuarioLogueado(perfil);
        }

        if (organizacionId != null) {
            dto.setOrganizacionAsociada(this.obtenerOrganizacionSegura(organizacionId));
            dto.setUbicacionMapa(this.obtenerGeolocalizacionSegura(organizacionId));
        }

        dto.setListaMascotas(this.obtenerMascotasSeguras());

        dto.setCoincidenciasCriticas(this.obtenerCoincidenciasSeguras());
        if (dto.getCoincidenciasCriticas() != null) {
            dto.setContadorAlertasPendientes(dto.getCoincidenciasCriticas().size());
        } else {
            dto.setContadorAlertasPendientes(0);
        }

        dto.setBuzonNotificaciones(new ArrayList<>());

        return dto;
    }

    @CircuitBreaker(name = "organizacionService", fallbackMethod = "fallbackOrganizacion")
    private OrganizacionBffDTO obtenerOrganizacionSegura(Long organizacionId) {
        return organizacionClient.buscarPorId(organizacionId);
    }
    public OrganizacionBffDTO fallbackOrganizacion(Long organizacionId, Throwable e) {
        System.out.println("CB Activo: Error en Organización. Motivo: " + e.getMessage());
        return null;
    }

    @CircuitBreaker(name = "geolocalizacionService", fallbackMethod = "fallbackGeolocalizacion")
    private GeolocalizacionBffDTO obtenerGeolocalizacionSegura(Long organizacionId) {
        return geolocalizacionClient.obtenerPorId(organizacionId);
    }
    public GeolocalizacionBffDTO fallbackGeolocalizacion(Long organizacionId, Throwable e) {
        System.out.println("CB Activo: Error en Mapa. Motivo: " + e.getMessage());
        return null;
    }

    @CircuitBreaker(name = "petService", fallbackMethod = "fallbackMascotas")
    private List<PetBffDTO> obtenerMascotasSeguras() {
        var mascotas = petClient.listar();
        return mascotas != null ? mascotas : new ArrayList<>();
    }
    public List<PetBffDTO> fallbackMascotas(Throwable e) {
        System.out.println("CB Activo: Error en Mascotas. Motivo: " + e.getMessage());
        return new ArrayList<>();
    }

    @CircuitBreaker(name = "coincidenciaService", fallbackMethod = "fallbackCoincidencias")
    private List<CoincidenciasBffDTO> obtenerCoincidenciasSeguras() {
        var coincidencias = coincidenciaClient.listarTodas();
        return coincidencias != null ? coincidencias : new ArrayList<>();
    }
    public List<CoincidenciasBffDTO> fallbackCoincidencias(Throwable e) {
        System.out.println("CB Activo: Error en Coincidencias. Motivo: " + e.getMessage());
        return new ArrayList<>();
    }
}