package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class PetBffDTO {
    private Long id;
    private String nombre;
    private String especie; // PERRO, GATO
    private String raza;
    private Integer edadAproximada;
    private String estado; // PERDIDO, ENCONTRADO, ADOPTADO
    private Long organizacionId;
}