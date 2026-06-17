package com.sanosysalvos.bff.dto;

import lombok.Data;
import java.util.Set;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;

    private String email;
    private String firstName;
    private String lastName;

    private Set<String> roles;
}