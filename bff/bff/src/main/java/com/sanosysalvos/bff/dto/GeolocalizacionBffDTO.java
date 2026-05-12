package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class GeolocalizacionBffDTO {
    private Long id;
    private String tipoEntidad; // USUARIO, ORGANIZACION, PET
    private Long entidadId;
    private Double latitud;
    private Double longitud;
    private String direccionAproximada;
}