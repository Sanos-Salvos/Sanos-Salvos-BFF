package com.sanosysalvos.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetBffDTO {
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private String estado;
    private Double lat;
    private Double lng;
    private String comuna;
    private String contacto;
    private String imagen;
    private String color;
    private String tamano;
    private String sexo;
    private Integer edad;
    private String tipoReporte;
}
