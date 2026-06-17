package com.sanosysalvos.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionBffDTO {
    private Long id;
    private String message;
    private String recipient;
    private LocalDateTime timestamp;
}