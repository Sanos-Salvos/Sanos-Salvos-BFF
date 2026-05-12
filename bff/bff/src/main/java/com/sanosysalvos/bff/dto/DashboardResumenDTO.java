package com.sanosysalvos.bff.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardResumenDTO {
    private UsuarioBffDTO usuarioLogueado;
    private OrganizacionBffDTO organizacionAsociada;
    private GeolocalizacionBffDTO ubicacionMapa;
    private List<PetBffDTO> listaMascotas;
    private List<CoincidenciaBffDTO> coincidenciasCriticas;
    private List<NotificacionBffDTO> buzonNotificaciones;
    private int contadorAlertasPendientes;
}