package xyz.fredyroman.biblioteca.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import xyz.fredyroman.biblioteca.model.Prestamo;
import xyz.fredyroman.biblioteca.model.TipoUsuario;
import xyz.fredyroman.biblioteca.repository.IPrestamoRepository;
import xyz.fredyroman.biblioteca.repository.ITipoUsuarioRepository;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PrestamoServiceTest {

    @Mock
    private IPrestamoRepository iPrestamoRepository;

    @Mock
    private ITipoUsuarioRepository iTipoUsuarioRepository;

    @InjectMocks
    private PrestamoService prestamoService;

    private Prestamo prestamo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        prestamo = new Prestamo();
        prestamo.setTipoUsuario(new TipoUsuario());
        prestamo.setIdentificacionUsuario("1053790852");
        prestamo.setIsbn("A001245DF");
        prestamo.setFechaMaximadevolucion(new Date());
        prestamo.setId(new Long("11"));
    }

    @Test
    void savePrestamo() {
    }

    @Test
    void validarCamposObligatorios() {
    }

    @Test
    void getAllPrestamo() {
        when(iPrestamoRepository.findAll()).thenReturn(Arrays.asList(prestamo));
        assertNotNull(prestamoService.getAllPrestamo(0,0,false));
    }

    @Test
    void findAll() {
        when(iPrestamoRepository.findAll()).thenReturn(Arrays.asList(prestamo));
        assertNotNull(prestamoService.findAll());
    }


    @Test
    void findById() {
    }

    @Test
    void deletePrestamo() {
    }

    @Test
    void updatePrestamo() {
    }

    @Test
    void existeRegistrosUsuarioInvitado() {
    }

    @Test
    void existById() {
    }

    @Test
    void existeTipoUsuario() {
    }

    @Test
    void calcularFechasDevolucionLibro() {
    }

    @Test
    void sumarDiasFechaDevolucion() {
    }
}