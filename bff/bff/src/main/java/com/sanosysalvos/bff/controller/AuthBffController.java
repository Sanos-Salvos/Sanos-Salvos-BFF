package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.AuthRequestDTO;
import com.sanosysalvos.bff.dto.AuthResponseDTO;
import com.sanosysalvos.bff.service.AuthBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/auth")
@CrossOrigin(origins = "*")
public class AuthBffController {

    private final AuthBffService service;

    public AuthBffController(AuthBffService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> procesarIngreso(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(service.loginYAutenticar(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registrarUsuario(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(service.registrarUsuario(request));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> chequearToken(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(service.validarFronteraToken(authHeader));
    }
}
