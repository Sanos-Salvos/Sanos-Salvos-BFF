package com.sanosysalvos.bff.dto;

import lombok.Data;

@Data
public class CoincidenciasBffDTO {
    private Long id;
    private Long petId;
    private Long orgId;
    private String estado;

}