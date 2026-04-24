package tests.rolgar2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.rolgar2.AlianzaRolgar2;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.entidad.Jugador;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase AlianzaRolgar2.
 */
public class AlianzaRolgar2Test {
    private AlianzaRolgar2<Jugador> alianza;
    private Jugador lider;
    private Jugador miembro;

    @BeforeEach
    public void setUp() throws IOException {
        ConfiguracionesRolgar2.inicializar();
        lider = new Jugador("Líder", 5);
        miembro = new Jugador("Miembro", 3);
        alianza = new AlianzaRolgar2<>("AlianzaTest", lider);
    }

    @Test
    public void testCrearAlianzaRolgar2() {
        assertNotNull(alianza);
        assertEquals("AlianzaTest", alianza.getNombre());
        assertEquals(lider, alianza.getLider());
    }

    @Test
    public void testAgregarMiembro() {
        alianza.agregarMiembro(miembro);
        assertTrue(alianza.estaEnAlianza(miembro));
    }

    @Test
    public void testQuitarMiembro() {
        alianza.agregarMiembro(miembro);
        alianza.quitarMiembro(miembro);
        assertFalse(alianza.estaEnAlianza(miembro));
    }
}
