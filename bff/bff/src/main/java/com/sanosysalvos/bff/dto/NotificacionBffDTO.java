package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class NotificacionBffDTO {
    private Long id;
    private Long usuarioId;
    private String titulo;
    private String mensaje;
    private String medio; // EMAIL, SMS, PUSH
    private boolean leido;
}