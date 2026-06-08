package com.sanosysalvos.bff.service;

import com.sanosysalvos.bff.client.*;
import com.sanosysalvos.bff.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardBffServiceTest {

    @Mock
    private UsuarioClient usuarioClient;
    @Mock
    private OrganizacionClient organizacionClient;
    @Mock
    private GeolocalizacionClient geolocalizacionClient;
    @Mock
    private PetClient petClient;
    @Mock
    private CoincidenciaClient coincidenciaClient;
    @Mock
    private NotificacionClient notificacionClient;

    @InjectMocks
    private DashboardBffService service;

    @Test
    void construirDashboardCompleto_todoDisponible_deberiaRetornarTodo() {
        UsuarioBffDTO usuario = new UsuarioBffDTO();
        OrganizacionBffDTO org = new OrganizacionBffDTO();
        GeolocalizacionBffDTO geo = new GeolocalizacionBffDTO();
        PetBffDTO pet = PetBffDTO.builder().id(1L).nombre("Firulais").build();
        CoincidenciaBffDTO coinc = new CoincidenciaBffDTO();
        NotificacionBffDTO notif = new NotificacionBffDTO();

        when(usuarioClient.obtenerPerfilUsuario(1L)).thenReturn(usuario);
        when(organizacionClient.obtenerPorId(1L)).thenReturn(org);
        when(geolocalizacionClient.obtenerCoordenadas("ORGANIZACION", 1L)).thenReturn(geo);
        when(petClient.listarPorOrg(1L)).thenReturn(List.of(pet));
        when(coincidenciaClient.obtenerCoincidencias(1L)).thenReturn(List.of(coinc));
        when(notificacionClient.obtenerAlertasUsuario(1L)).thenReturn(List.of(notif));

        DashboardResumenDTO resultado = service.construirDashboardCompleto(1L, 1L);

        assertNotNull(resultado);
        assertNotNull(resultado.getUsuarioLogueado());
        assertNotNull(resultado.getOrganizacionAsociada());
        assertNotNull(resultado.getUbicacionMapa());
        assertEquals(1, resultado.getListaMascotas().size());
        assertEquals(1, resultado.getCoincidenciasCriticas().size());
        assertEquals(1, resultado.getContadorAlertasPendientes());
        assertEquals(1, resultado.getBuzonNotificaciones().size());
    }

    @Test
    void construirDashboardCompleto_todoFalla_deberiaRetornarValoresNulosOVacios() {
        when(usuarioClient.obtenerPerfilUsuario(anyLong())).thenThrow(new RuntimeException("down"));
        when(organizacionClient.obtenerPorId(anyLong())).thenThrow(new RuntimeException("down"));
        when(geolocalizacionClient.obtenerCoordenadas(anyString(), anyLong())).thenThrow(new RuntimeException("down"));
        when(petClient.listarPorOrg(anyLong())).thenThrow(new RuntimeException("down"));
        when(coincidenciaClient.obtenerCoincidencias(anyLong())).thenThrow(new RuntimeException("down"));
        when(notificacionClient.obtenerAlertasUsuario(anyLong())).thenThrow(new RuntimeException("down"));

        DashboardResumenDTO resultado = service.construirDashboardCompleto(1L, 1L);

        assertNotNull(resultado);
        assertNull(resultado.getUsuarioLogueado());
        assertNull(resultado.getOrganizacionAsociada());
        assertNull(resultado.getUbicacionMapa());
        assertTrue(resultado.getListaMascotas().isEmpty());
        assertTrue(resultado.getCoincidenciasCriticas().isEmpty());
        assertEquals(0, resultado.getContadorAlertasPendientes());
        assertTrue(resultado.getBuzonNotificaciones().isEmpty());
    }

    @Test
    void construirDashboardCompleto_parcialmenteDisponible_deberiaManejarFallasParciales() {
        UsuarioBffDTO usuario = new UsuarioBffDTO();
        when(usuarioClient.obtenerPerfilUsuario(1L)).thenReturn(usuario);
        when(organizacionClient.obtenerPorId(1L)).thenThrow(new RuntimeException("down"));
        when(geolocalizacionClient.obtenerCoordenadas("ORGANIZACION", 1L)).thenThrow(new RuntimeException("down"));
        when(petClient.listarPorOrg(1L)).thenReturn(List.of());
        when(coincidenciaClient.obtenerCoincidencias(1L)).thenReturn(List.of());
        when(notificacionClient.obtenerAlertasUsuario(1L)).thenReturn(List.of());

        DashboardResumenDTO resultado = service.construirDashboardCompleto(1L, 1L);

        assertNotNull(resultado);
        assertNotNull(resultado.getUsuarioLogueado());
        assertNull(resultado.getOrganizacionAsociada());
        assertNull(resultado.getUbicacionMapa());
        assertEquals(0, resultado.getContadorAlertasPendientes());
    }
}
