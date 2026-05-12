package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class UsuarioBffDTO {
    private Long id;
    private String username;
    private String nombre;
    private String apellido;
    private String correo;
    private String rol;
}