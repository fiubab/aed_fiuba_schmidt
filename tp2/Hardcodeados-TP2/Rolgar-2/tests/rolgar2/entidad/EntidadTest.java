package tests.rolgar2.entidad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.rolgar2.entidad.Entidad;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Entidad.
 */
public class EntidadTest {
    private Entidad entidad;

    @BeforeEach
    public void setUp() {
        entidad = new Entidad("Entidad Test", 100, 10, 5);
    }

    @Test
    public void testCrearEntidad() {
        assertNotNull(entidad);
        assertEquals("Entidad Test", entidad.getNombre());
        assertEquals(100, entidad.getVida());
        assertEquals(10, entidad.getFuerza());
        assertEquals(5, entidad.getIniciativa());
    }

    @Test
    public void testGetVida() {
        assertEquals(100, entidad.getVida());
    }

    @Test
    public void testSetVida() {
        entidad.setVida(50);
        assertEquals(50, entidad.getVida());
    }

    @Test
    public void testEstaEnCombate() {
        assertFalse(entidad.estaEnCombate());
    }
}
