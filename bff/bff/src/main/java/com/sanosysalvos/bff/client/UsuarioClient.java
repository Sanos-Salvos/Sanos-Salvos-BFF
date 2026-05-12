package com.sanosysalvos.bff.client;

import com.sanosysalvos.bff.dto.UsuarioBffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-client", url = "${microservicio.usuarios.url}")
public interface UsuarioClient {
    @GetMapping("/perfil/{id}")
    UsuarioBffDTO obtenerPerfilUsuario(@PathVariable("id") Long id);
}