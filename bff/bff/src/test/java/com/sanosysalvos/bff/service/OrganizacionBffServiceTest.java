package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.OrganizacionClient;
import com.sanosysalvos.bff.dto.OrganizacionBffDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizacionBffServiceTest {

    @Mock
    private OrganizacionClient client;

    @InjectMocks
    private OrganizacionBffService service;

    private OrganizacionBffDTO buildOrg() {
        OrganizacionBffDTO dto = new OrganizacionBffDTO();
        dto.setId(1L);
        dto.setNombre("Fundacion Patitas");
        dto.setTipo("REFUGIO");
        dto.setTelefono("123456789");
        dto.setEmail("info@patitas.cl");
        return dto;
    }

    @Test
    void listarTodas_deberiaRetornarLista() {
        when(client.listarTodas()).thenReturn(List.of(buildOrg()));

        List<OrganizacionBffDTO> resultado = service.listarTodas();

        assertEquals(1, resultado.size());
        assertEquals("Fundacion Patitas", resultado.get(0).getNombre());
    }

    @Test
    void buscarPorId_existente_deberiaRetornarDTO() {
        when(client.obtenerPorId(1L)).thenReturn(buildOrg());

        OrganizacionBffDTO resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Fundacion Patitas", resultado.getNombre());
    }

    @Test
    void buscarPorId_noExistente_deberiaLanzarExcepcion() {
        when(client.obtenerPorId(99L)).thenThrow(new RuntimeException("Not found"));

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void crear_deberiaRetornarDTO() {
        OrganizacionBffDTO input = buildOrg();
        input.setId(null);

        when(client.crear(any(OrganizacionBffDTO.class))).thenReturn(buildOrg());

        OrganizacionBffDTO resultado = service.crear(input);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void actualizar_deberiaRetornarDTOActualizado() {
        OrganizacionBffDTO update = buildOrg();
        update.setNombre("Nuevo Nombre");

        when(client.actualizar(eq(1L), any(OrganizacionBffDTO.class))).thenReturn(update);

        OrganizacionBffDTO resultado = service.actualizar(1L, update);

        assertEquals("Nuevo Nombre", resultado.getNombre());
    }

    @Test
    void eliminar_deberiaLlamarClient() {
        doNothing().when(client).eliminar(1L);

        service.eliminar(1L);

        verify(client).eliminar(1L);
    }
}
