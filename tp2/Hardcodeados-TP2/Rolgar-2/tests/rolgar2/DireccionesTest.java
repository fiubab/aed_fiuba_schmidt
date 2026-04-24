package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Direcciones;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para el enum Direcciones.
 */
public class DireccionesTest {

    @Test
    public void testDireccionesExisten() {
        assertNotNull(Direcciones.ARRIBA);
        assertNotNull(Direcciones.ABAJO);
        assertNotNull(Direcciones.IZQUIERDA);
        assertNotNull(Direcciones.DERECHA);
    }

    @Test
    public void testDireccionesValores() {
        assertEquals("Norte", Direcciones.ARRIBA.toString());
        assertEquals("Sur", Direcciones.ABAJO.toString());
    }
}
