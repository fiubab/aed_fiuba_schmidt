package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Acciones;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para el enum Acciones.
 */
public class AccionesTest {

    @Test
    public void testAccionesExisten() {
        assertNotNull(Acciones.MOVER);
        assertNotNull(Acciones.ATACAR);
        assertNotNull(Acciones.SELECCIONAR_CARTA);
        assertNotNull(Acciones.CREAR_ALIANZA);
    }

    @Test
    public void testAccionesValores() {
        assertEquals("Mover", Acciones.MOVER.toString());
        assertEquals("Atacar", Acciones.ATACAR.toString());
    }
}
