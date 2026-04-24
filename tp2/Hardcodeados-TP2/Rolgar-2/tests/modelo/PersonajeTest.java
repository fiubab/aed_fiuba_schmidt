package tests.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.modelo.Personaje;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Personaje.
 */
public class PersonajeTest {
    private Personaje personaje;

    @BeforeEach
    public void setUp() {
        personaje = new Personaje("Héroe");
    }

    @Test
    public void testCrearPersonaje() {
        assertNotNull(personaje);
        assertEquals("Héroe", personaje.getNombre());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Héroe", personaje.getNombre());
    }

    @Test
    public void testCrearPersonajeConNombreVacio() {
        assertThrows(RuntimeException.class, () -> new Personaje(""));
    }

    @Test
    public void testCrearPersonajeConNombreNull() {
        assertThrows(RuntimeException.class, () -> new Personaje(null));
    }

    @Test
    public void testCrearPersonajeConCaracteresNoAlfabeticos() {
        assertThrows(RuntimeException.class, () -> new Personaje("Héroe123"));
    }

    @Test
    public void testEquals() {
        Personaje personaje2 = new Personaje("Héroe");
        assertEquals(personaje, personaje2);
    }

    @Test
    public void testNotEquals() {
        Personaje personaje2 = new Personaje("Villano");
        assertNotEquals(personaje, personaje2);
    }

    @Test
    public void testHashCode() {
        Personaje personaje2 = new Personaje("Héroe");
        assertEquals(personaje.hashCode(), personaje2.hashCode());
    }

    @Test
    public void testToString() {
        assertTrue(personaje.toString().contains("Héroe"));
    }
}
