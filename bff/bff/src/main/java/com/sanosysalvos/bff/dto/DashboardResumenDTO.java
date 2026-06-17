package com.sanosysalvos.bff.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardResumenDTO {
private AuthResponseDTO usuarioLogueado;

    private OrganizacionBffDTO organizacionAsociada;
    private GeolocalizacionBffDTO ubicacionMapa;
    private List<PetBffDTO> listaMascotas;
    private List<CoincidenciasBffDTO> coincidenciasCriticas;
    private List<NotificacionBffDTO> buzonNotificaciones;
    private int contadorAlertasPendientes;
}