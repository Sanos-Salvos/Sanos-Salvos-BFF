package com.sanosysalvos.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizacionBffDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private String direccion;
    private String telefono;
    private String email;
}