package tests.rolgar2.entidad;

import org.junit.jupiter.api.Test;
import src.rolgar2.entidad.Efectos;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para el enum Efectos.
 */
public class EfectosTest {

    @Test
    public void testEfectosExisten() {
        assertNotNull(Efectos.ESCUDO);
        assertNotNull(Efectos.INVISIBILIDAD);
    }

    @Test
    public void testEfectosValores() {
        assertEquals(2, Efectos.values().length);
    }
}
