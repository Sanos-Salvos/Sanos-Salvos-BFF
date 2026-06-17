package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private boolean autenticado;
    private String username;
    private String rol;
}