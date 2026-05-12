package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String tipoToken; // Bearer
    private String username;
    private String rol;
}