package com.sanosysalvos.bff.controller;

import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import com.sanosysalvos.bff.service.OrganizacionBffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizacionBffControllerTest {

    @Mock
    private OrganizacionBffService service;

    @InjectMocks
    private OrganizacionBffController controller;

    private OrganizacionBffDTO buildOrg() {
        OrganizacionBffDTO dto = new OrganizacionBffDTO();
        dto.setId(1L);
        dto.setNombre("Fundacion Patitas");
        dto.setTipo("REFUGIO");
        return dto;
    }

    @Test
    void listarTodas_deberiaRetornarLista() {
        when(service.listarTodas()).thenReturn(List.of(buildOrg()));

        ResponseEntity<List<OrganizacionBffDTO>> response = controller.listarTodas();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void buscarPorId_deberiaRetornarDTO() {
        when(service.buscarPorId(1L)).thenReturn(buildOrg());

        ResponseEntity<OrganizacionBffDTO> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Fundacion Patitas", response.getBody().getNombre());
    }

    @Test
    void crear_deberiaRetornarDTO() {
        when(service.crear(any(OrganizacionBffDTO.class))).thenReturn(buildOrg());

        ResponseEntity<OrganizacionBffDTO> response = controller.crear(buildOrg());

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void actualizar_deberiaRetornarDTO() {
        OrganizacionBffDTO update = buildOrg();
        update.setNombre("Nuevo");
        when(service.actualizar(eq(1L), any(OrganizacionBffDTO.class))).thenReturn(update);

        ResponseEntity<OrganizacionBffDTO> response = controller.actualizar(1L, update);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Nuevo", response.getBody().getNombre());
    }

    @Test
    void eliminar_deberiaRetornarMensaje() {
        doNothing().when(service).eliminar(1L);

        ResponseEntity<String> response = controller.eliminar(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Organización eliminada correctamente", response.getBody());
    }
}
