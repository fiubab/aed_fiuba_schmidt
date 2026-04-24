package tests.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.modelo.Alianza;
import src.modelo.Personaje;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Alianza.
 */
public class AlianzaTest {
    private Alianza alianza;
    private Personaje personaje1;
    private Personaje personaje2;

    @BeforeEach
    public void setUp() {
        alianza = new Alianza("AlianzaTest");
        personaje1 = new Personaje("PersonajeUno");
        personaje2 = new Personaje("PersonajeDos");
    }

    @Test
    public void testCrearAlianza() {
        assertNotNull(alianza);
        assertEquals("AlianzaTest", alianza.getNombre());
        assertTrue(alianza.estaVacia());
    }

    @Test
    public void testAgregarMiembro() {
        alianza.agregarMiembro(personaje1);
        assertTrue(alianza.estaEnAlianza(personaje1));
        assertEquals(1, alianza.getCantidadMiembros());
    }

    @Test
    public void testQuitarMiembro() {
        alianza.agregarMiembro(personaje1);
        alianza.quitarMiembro(personaje1);
        assertFalse(alianza.estaEnAlianza(personaje1));
        assertTrue(alianza.estaVacia());
    }

    @Test
    public void testEstaEnAlianza() {
        alianza.agregarMiembro(personaje1);
        assertTrue(alianza.estaEnAlianza(personaje1));
        assertFalse(alianza.estaEnAlianza(personaje2));
    }

    @Test
    public void testSetNombre() {
        alianza.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", alianza.getNombre());
    }

    @Test
    public void testAgregarMiembroDuplicado() {
        alianza.agregarMiembro(personaje1);
        assertThrows(RuntimeException.class, () -> alianza.agregarMiembro(personaje1));
    }

    @Test
    public void testQuitarMiembroNoExistente() {
        assertThrows(RuntimeException.class, () -> alianza.quitarMiembro(personaje1));
    }
}
