package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class PetBffDTO {
    private Long id;
    private String nombre;
    private String especie;    // NUEVO
    private String raza;
    private Integer edad;
    private String estado;     // NUEVO (Perdido, Encontrado, Adopción)
    private String descripcion;
    private Double lat;        // NUEVO (Mapa)
    private Double lng;        // NUEVO (Mapa)
    private String comuna;     // NUEVO (Filtros)
    private String contacto;
}