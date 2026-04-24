package tests.utils;

import org.junit.jupiter.api.Test;
import src.utils.SistemaUtiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase SistemaUtiles.
 */
public class SistemaUtilesTest {

    @Test
    public void testEsperarPositivo() {
        long inicio = System.currentTimeMillis();
        SistemaUtiles.esperar(100);
        long fin = System.currentTimeMillis();
        assertTrue(fin - inicio >= 100);
    }

    @Test
    public void testEsperarCero() {
        assertDoesNotThrow(() -> SistemaUtiles.esperar(0));
    }

    @Test
    public void testEsperarNegativo() {
        assertThrows(RuntimeException.class, () -> SistemaUtiles.esperar(-1));
    }

    @Test
    public void testGenerarRutaAbsoluta() {
        String ruta = SistemaUtiles.generarRutaAbsoluta("src/test.txt");
        assertNotNull(ruta);
        assertTrue(ruta.contains("src"));
        assertTrue(ruta.contains("test.txt"));
    }
}
