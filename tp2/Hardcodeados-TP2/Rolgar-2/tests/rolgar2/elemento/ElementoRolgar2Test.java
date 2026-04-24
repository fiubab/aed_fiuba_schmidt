package tests.rolgar2.elemento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.rolgar2.elemento.ElementoRolgar2;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase ElementoRolgar2.
 */
public class ElementoRolgar2Test {
    private ElementoRolgar2 elemento;

    @BeforeEach
    public void setUp() {
        elemento = new ElementoRolgar2("Elemento Test");
    }

    @Test
    public void testCrearElementoRolgar2() {
        assertNotNull(elemento);
        assertEquals("Elemento Test", elemento.getNombre());
    }

    @Test
    public void testGetPropiedades() {
        assertNotNull(elemento.getPropiedades());
        assertTrue(elemento.getPropiedades().isEmpty());
    }
}
