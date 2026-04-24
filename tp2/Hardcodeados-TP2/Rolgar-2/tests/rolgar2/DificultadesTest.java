package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Dificultades;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para el enum Dificultades.
 */
public class DificultadesTest {

    @Test
    public void testDificultadesExisten() {
        assertNotNull(Dificultades.FACIL);
        assertNotNull(Dificultades.MEDIO);
        assertNotNull(Dificultades.DIFICIL);
        assertNotNull(Dificultades.CUSTOM);
    }

    @Test
    public void testDificultadesValores() {
        assertEquals("Fácil", Dificultades.FACIL.toString());
        assertEquals("Medio", Dificultades.MEDIO.toString());
    }
}
