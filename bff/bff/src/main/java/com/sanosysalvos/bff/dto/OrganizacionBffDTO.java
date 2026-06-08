package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class OrganizacionBffDTO {
    private Long id;
    private String nombre;
    private String tipo; // VETERINARIA, REFUGIO
    private String direccion;
    private String telefono;
    private String email;
}
