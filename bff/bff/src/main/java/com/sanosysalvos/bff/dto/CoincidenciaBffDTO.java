package com.sanosysalvos.bff.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CoincidenciaBffDTO {
    private Long id;
    private Long petId;
    private Long organizacionId;
    private Double porcentajeSimilitud;
    private String estado; // PENDIENTE, VERIFICADO
    private LocalDateTime fechaCruce;
}