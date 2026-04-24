package tests.utils;

import src.utils.Temporizador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TemporizadorTest {

    @BeforeEach
    void setUp() {
        Temporizador.reiniciar();
    }

    // -------------------------------------------------------------------------
    // Iniciar temporizador
    // -------------------------------------------------------------------------

    @Test
    void iniciarDeberiaPonerTemporizadorEnMarcha() {
        Temporizador.iniciar();
        
        assertTrue(Temporizador.estaEnMarcha());
    }

    @Test
    void iniciarCuandoYaEstaEnMarchaDeberiaLanzarExcepcion() {
        Temporizador.iniciar();
        
        assertThrows(RuntimeException.class, Temporizador::iniciar);
    }

    // -------------------------------------------------------------------------
    // Pausar temporizador
    // -------------------------------------------------------------------------

    @Test
    void pausarDeberiaDetenerTemporizador() {
        Temporizador.iniciar();
        Temporizador.pausar();
        
        assertFalse(Temporizador.estaEnMarcha());
    }
    @Test
    void pausarCuandoNoEstaEnMarchaDeberiaLanzarExcepcion() {
        assertThrows(RuntimeException.class, Temporizador::pausar);
    }

    @Test
    void pausarDeberiaGuardarTiempoTranscurrido() throws InterruptedException {
        Temporizador.iniciar();
        Thread.sleep(100);
        Temporizador.pausar();
        
        Duration tiempo = Temporizador.getTiempoTranscurrido();
        assertTrue(tiempo.toMillis() >= 100);
    }

    // -------------------------------------------------------------------------
    // Reiniciar temporizador
    // -------------------------------------------------------------------------

    @Test
    void reiniciarDeberiaResetearTiempo() throws InterruptedException {
        Temporizador.iniciar();
        Thread.sleep(100);
        Temporizador.pausar();
        
        Temporizador.reiniciar();
        
        assertEquals(Duration.ZERO, Temporizador.getTiempoTranscurrido());
        assertFalse(Temporizador.estaEnMarcha());
    }

    // -------------------------------------------------------------------------
    // Tiempo transcurrido
    // -------------------------------------------------------------------------

    @Test
    void getTiempoTranscurridoDeberiaRetornarCero() {
        assertEquals(Duration.ZERO, Temporizador.getTiempoTranscurrido());
    }

    @Test
    void getTiempoTranscurridoDeberiaAumentarMientrasEstaEnMarcha() throws InterruptedException {
        Temporizador.iniciar();
        Thread.sleep(100);
        
        Duration tiempo = Temporizador.getTiempoTranscurrido();
        assertTrue(tiempo.toMillis() >= 100);
    }

    @Test
    void getTiempoTranscurridoDeberiaAcumularDespuesDePausas() throws InterruptedException {
        Temporizador.iniciar();
        Thread.sleep(50);
        Temporizador.pausar();
        
        Duration primerTiempo = Temporizador.getTiempoTranscurrido();
        
        Temporizador.iniciar();
        Thread.sleep(50);
        Temporizador.pausar();
        
        Duration segundoTiempo = Temporizador.getTiempoTranscurrido();
        
        assertTrue(segundoTiempo.toMillis() > primerTiempo.toMillis());
        assertTrue(segundoTiempo.toMillis() >= 100);
    }

    // -------------------------------------------------------------------------
    // Tiempo formateado
    // -------------------------------------------------------------------------

    @Test
    void getTiempoFormateadoDeberiaRetornarFormatoCorrecto() {
        String tiempo = Temporizador.getTiempoFormateado();
        
        assertNotNull(tiempo);
        assertTrue(tiempo.matches("\\d{2}:\\d{2}:\\d{2}"));
        assertEquals("00:00:00", tiempo);
    }

    @Test
    void getTiempoFormateadoDeberiaFormatearCorrectamente() throws InterruptedException {
        Temporizador.iniciar();
        Thread.sleep(1100);
        Temporizador.pausar();
        
        String tiempo = Temporizador.getTiempoFormateado();
        
        assertTrue(tiempo.matches("\\d{2}:\\d{2}:\\d{2}"));
        assertTrue(tiempo.contains("00:00:01") || tiempo.contains("00:00:02"));
    }

    // -------------------------------------------------------------------------
    // Estado del temporizador
    // -------------------------------------------------------------------------

    @Test
    void estaEnMarchaDeberiaRetornarFalsePorDefecto() {
        assertFalse(Temporizador.estaEnMarcha());
    }

    @Test
    void estaEnMarchaDeberiaRetornarTrueDespuesDeIniciar() {
        Temporizador.iniciar();
        
        assertTrue(Temporizador.estaEnMarcha());
    }

    @Test
    void estaEnMarchaDeberiaRetornarFalseDespuesDePausar() {
        Temporizador.iniciar();
        Temporizador.pausar();
        
        assertFalse(Temporizador.estaEnMarcha());
    }
}
