package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class GeolocalizacionBffDTO {
    private Long id;
    private Long mascotaId;
    private String direccion;
    private String ciudad;
    private Double latitud;
    private Double longitud;
}