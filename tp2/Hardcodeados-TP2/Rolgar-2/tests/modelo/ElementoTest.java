package tests.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.modelo.Elemento;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Elemento.
 */
public class ElementoTest {
    private Elemento elemento;

    @BeforeEach
    public void setUp() {
        elemento = new Elemento("Espada");
    }

    @Test
    public void testCrearElemento() {
        assertNotNull(elemento);
        assertEquals("Espada", elemento.getNombre());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Espada", elemento.getNombre());
    }

    @Test
    public void testCrearElementoConNombreVacio() {
        assertThrows(RuntimeException.class, () -> new Elemento(""));
    }

    @Test
    public void testCrearElementoConNombreNull() {
        assertThrows(RuntimeException.class, () -> new Elemento(null));
    }

    @Test
    public void testCrearElementoConCaracteresNoAlfabeticos() {
        assertThrows(RuntimeException.class, () -> new Elemento("Espada123"));
    }

    @Test
    public void testEquals() {
        Elemento elemento2 = new Elemento("Espada");
        assertEquals(elemento, elemento2);
    }

    @Test
    public void testNotEquals() {
        Elemento elemento2 = new Elemento("Escudo");
        assertNotEquals(elemento, elemento2);
    }

    @Test
    public void testHashCode() {
        Elemento elemento2 = new Elemento("Espada");
        assertEquals(elemento.hashCode(), elemento2.hashCode());
    }
}
